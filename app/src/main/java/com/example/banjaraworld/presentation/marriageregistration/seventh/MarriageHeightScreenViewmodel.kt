package com.example.banjaraworld.presentation.marriageregistration.seventh

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banjaraworld.presentation.marriageregistration.secondscreenmarriage.MarriageSecondScreenViewModel.ValidationEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MarriageHeightScreenViewmodel @Inject constructor() : ViewModel() {

    private val _state = MarriageHeightScreenState()
    val state = _state

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()


    fun onEvent(event: MarriageHeightScreenEvent) {
        when (event) {
            is MarriageHeightScreenEvent.Continue -> {
                submitData()
            }

            is MarriageHeightScreenEvent.HeightChanged -> {
                _state.height = event.height
                _state.selectedUnit = event.selectedUnit
            }
        }
    }

    private fun submitData() {
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }
}