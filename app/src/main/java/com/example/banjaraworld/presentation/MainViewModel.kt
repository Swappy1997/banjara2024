package com.example.banjaraworld.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {


    val visablePermissionDialog = mutableStateListOf<String>()

    fun dismissDialog() {
        visablePermissionDialog.removeLast()
    }

    fun onPermissionResult(permission: String, isGranted: Boolean) {

        if (!isGranted) {
            visablePermissionDialog.add(index = 0, permission)
        }
    }
}