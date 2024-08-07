package com.example.banjaraworld.presentation.marriageregistration.secondscreenmarriage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banjaraworld.domain.usecases.ValidateFirstName
import com.example.banjaraworld.domain.usecases.marriageregistration.marriagesecondscreen.ValidateLastNameUseCase
import com.example.banjaraworld.domain.usecases.marriageregistration.marriagesecondscreen.ValidateMiddleNameUseCase
import com.example.banjaraworld.domain.usecases.marriageregistration.marriagesecondscreen.ValidateMotherNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarriageSecondScreenViewModel @Inject constructor(
    private val validateFirstNameUseCase: ValidateFirstName,
    private val validateMiddleNameUseCase: ValidateMiddleNameUseCase,
    private val validateLastNameUseCase: ValidateLastNameUseCase,
    private val validateMotherNameUseCase: ValidateMotherNameUseCase
) : ViewModel() {

    var state by mutableStateOf(MarriageSecondScreenState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()


    fun onEvent(event: MarriageSecondScreenEvent) {
        when (event) {
            is MarriageSecondScreenEvent.FirstNameChanged -> {
                state = state.copy(firstName = event.firstName)
            }

            is MarriageSecondScreenEvent.MiddleNameChanged -> {
                state = state.copy(middleName = event.middleName)
            }

            is MarriageSecondScreenEvent.LastNameChanged -> {
                state = state.copy(lastName = event.lastName)
            }

            MarriageSecondScreenEvent.Continue -> {
                SubmitData()
            }

            is MarriageSecondScreenEvent.MotherNameChanged -> {
                state = state.copy(motherName = event.motherName)
            }
        }
    }

    fun SubmitData() {
        val firstNameResult = validateFirstNameUseCase.invoke(state.firstName)
        val middleNameResult = validateMiddleNameUseCase.invoke(state.middleName)
        val lastNameResult = validateLastNameUseCase.invoke(state.lastName)
        val motherNameResult = validateMotherNameUseCase.invoke(state.motherName)

        val hasError = listOf(
            firstNameResult,
            middleNameResult,
            lastNameResult,
            motherNameResult
        )

            .any {
                !it.successful
            }
        if (hasError) {
            state = state.copy(
                firstNameError = firstNameResult.errorMessage,
                middleNameError = middleNameResult.errorMessage,
                lastNameError = lastNameResult.errorMessage,
                motherNameError = motherNameResult.errorMessage
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