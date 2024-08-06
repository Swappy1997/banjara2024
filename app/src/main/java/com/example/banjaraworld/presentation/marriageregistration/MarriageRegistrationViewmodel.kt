package com.example.banjaraworld.presentation.marriageregistration

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MarriageRegistrationViewmodel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    val visiblePermissionDialog = mutableStateListOf<String>()

    fun dismissDialog() {
        visiblePermissionDialog.removeLastOrNull()
    }

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    private val _state = MutableStateFlow("")
    val state: StateFlow<String> = _state

    private val permissionIsGranted = MutableStateFlow(false)
    val isPermissionGranted: StateFlow<Boolean> = permissionIsGranted

    private val _city = MutableStateFlow("")
    val city: StateFlow<String> = _city

    fun onPermissionResult(permission: String, isGranted: Boolean) {
        if (isGranted) {
            viewModelScope.launch {
                fetchLocationAndUpdateStateCity()
                permissionIsGranted.value = true
            }
        }
        if (!isGranted) {
            visiblePermissionDialog.add(index = 0, permission)
            permissionIsGranted.value = false
        }
    }

    private fun fetchLocationAndUpdateStateCity() {
        val context = getApplication<Application>().applicationContext
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permissions are not granted, return or handle it appropriately
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                val geocoder = Geocoder(context, Locale.getDefault())
                val addresses: MutableList<Address>? =
                    geocoder.getFromLocation(it.latitude, it.longitude, 1)
                if (addresses != null) {
                    if (addresses.isNotEmpty()) {
                        val address: Address = addresses[0]
                        _state.value = address.adminArea ?: "Unknown State"
                        _city.value = address.locality ?: "Unknown City"
                    } else {
                        _state.value = "Unknown State"
                        _city.value = "Unknown City"
                    }
                }
            } ?: run {
                _state.value = "Unknown State"
                _city.value = "Unknown City"
            }
        }.addOnFailureListener { exception ->
            Log.e("LocationError", "Error getting location", exception)
            _state.value = "Unknown State"
            _city.value = "Unknown City"
        }
    }
}
