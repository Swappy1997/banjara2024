package com.example.banjaraworld.presentation.marriageregistration.fourthscreenmarriage

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr0xf00.easycrop.ImageCropper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MarriageFourthScreenViewmodel @Inject constructor() : ViewModel() {

    private val _imageUris = MutableStateFlow(listOf<Uri?>(null, null, null))
    val imageUris: StateFlow<List<Uri?>> get() = _imageUris

    private val imageCropper = ImageCropper()

    fun setImageUri(index: Int, uri: Uri) {
        viewModelScope.launch {
            val newList = _imageUris.value.toMutableList()
            newList[index] = uri
            _imageUris.emit(newList)
        }
    }


}