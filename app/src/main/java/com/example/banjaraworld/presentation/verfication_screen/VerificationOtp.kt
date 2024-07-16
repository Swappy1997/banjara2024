package com.example.banjaraworld.presentation.verfication_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banjaraworld.domain.usecases.ValidateOtp
import com.example.banjaraworld.presentation.login_screen.LoginViewModel.ValidationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationOtp @Inject constructor(private val validateOtp: ValidateOtp) : ViewModel() {

    var state by mutableStateOf(VerificationOtpState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()


    fun OnEvent(event: VerificationOtpEvent) {

        when (event) {
            is VerificationOtpEvent.OptChanged -> {
                state = state.copy(otp = event.otpValue)
            }

            VerificationOtpEvent.Verify -> {
                SubmitData()
            }
        }
    }

    fun SubmitData() {
        val otpResult = validateOtp.invoke(state.otp)
        val hasError = listOf(
            otpResult,
        )
            .any {
                !it.successful
            }
        if (hasError) {
            state = state.copy(
                otpError = otpResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }


}