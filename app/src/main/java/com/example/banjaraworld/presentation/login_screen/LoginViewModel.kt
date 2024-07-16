package com.example.banjaraworld.presentation.login_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banjaraworld.common.ConnectivityObserver
import com.example.banjaraworld.common.NetworkConnectivityObserver
import com.example.banjaraworld.domain.usecases.ValidateCheckBox
import com.example.banjaraworld.domain.usecases.ValidateFirstName
import com.example.banjaraworld.domain.usecases.ValidateMobileNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateMobileNumber: ValidateMobileNumber,
    private val validateFirstName: ValidateFirstName,
    private val validateCheckBox: ValidateCheckBox,
    private val networkConnectivityObserver: NetworkConnectivityObserver
) : ViewModel() {

    var state by mutableStateOf(LoginFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()


    private val _networkStatus =
        MutableStateFlow<ConnectivityObserver.Status>(ConnectivityObserver.Status())
    val networkStatus: StateFlow<ConnectivityObserver.Status> = _networkStatus

    fun onEvent(event: LoginFormEvent) {

        when (event) {
            is LoginFormEvent.FirstNameChanged -> {
                state = state.copy(firstName = event.firstName)
            }

            is LoginFormEvent.MobileChanged -> {
                state = state.copy(mobileNumber = event.mobileNumber)
            }

            LoginFormEvent.Continue -> {
                SubmitData()
            }

            is LoginFormEvent.CheckBoxChanged -> {
                state = state.copy(checkBox = event.isChecked)
            }
        }
    }

    init {
        observeNetwork()
    }

    private fun observeNetwork() {
        viewModelScope.launch {
            networkConnectivityObserver.observe().collect { status ->
                _networkStatus.value = status
            }
        }
    }

    fun SubmitData() {
        val mobileResult = validateMobileNumber.invoke(state.mobileNumber)
        val firstNameResult = validateFirstName.invoke(state.firstName)
        val checkBoxResult = validateCheckBox.invoke(state.checkBox)

        val hasError = listOf(
            mobileResult,
            firstNameResult,
            checkBoxResult
        )
            .any {
                !it.successful
            }
        if (hasError) {
            state = state.copy(
                mobileNumberError = mobileResult.errorMessage,
                firstNameError = firstNameResult.errorMessage,
                checkBoxError = checkBoxResult.errorMessage

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