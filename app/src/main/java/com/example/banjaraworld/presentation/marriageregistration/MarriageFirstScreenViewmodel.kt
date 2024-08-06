package com.example.banjaraworld.presentation.marriageregistration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banjaraworld.domain.usecases.marriageregistration.ValidateIsGenderSelectedUseCase
import com.example.banjaraworld.domain.usecases.marriageregistration.ValidateIsMarriageStatusSelectedUseCase
import com.example.banjaraworld.domain.usecases.marriageregistration.ValidateIsProfileCreatedForUseCase
import com.example.banjaraworld.presentation.login_screen.LoginViewModel.ValidationEvent
import com.example.banjaraworld.presentation.marriageregistration.firstscreenmarriage.MarriageFirstScreenEvent
import com.example.banjaraworld.presentation.marriageregistration.firstscreenmarriage.MarriageFirstScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarriageFirstScreenViewmodel @Inject constructor(
    private val validateIsGenderSelectedUseCase: ValidateIsGenderSelectedUseCase,
    private val validateIsMarriageStatusSelectedUseCase: ValidateIsMarriageStatusSelectedUseCase,
    private val validateIsProfileCreatedForUseCase: ValidateIsProfileCreatedForUseCase
) : ViewModel() {


    var state by mutableStateOf(MarriageFirstScreenState())


    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()


    fun onEvent(event: MarriageFirstScreenEvent) {
        when (event) {
            is MarriageFirstScreenEvent.GenderChanged -> {
                state = state.copy(isGenderSelected = event.gender)
            }

            MarriageFirstScreenEvent.Continue -> {
                SubmitData()
            }

            is MarriageFirstScreenEvent.MarriageStatusChanged -> {
                state = state.copy(isMarriageStatusSelected = event.marriageStatus)
            }

            is MarriageFirstScreenEvent.ProfileCreatedForChanged -> {
                state = state.copy(isProfileCreatedForSelected = event.profileCreatedFor)
            }
        }
    }

    fun SubmitData() {
        val genderResult = validateIsGenderSelectedUseCase.invoke(state.isGenderSelected)
        val marriageStatusResult =
            validateIsMarriageStatusSelectedUseCase.invoke(state.isMarriageStatusSelected)
        val profileCreatedForResult =
            validateIsProfileCreatedForUseCase.invoke(state.isProfileCreatedForSelected)
        val hasError = listOf(
            genderResult,
            marriageStatusResult,
            profileCreatedForResult
        )
            .any {
                !it.successful
            }
        if (hasError) {
            state = state.copy(
                isGenderSelectedError = genderResult.errorMessage,
                isMarriageStatusSelectedError = marriageStatusResult.errorMessage,
                isProfileCreatedForSelectedError = profileCreatedForResult.errorMessage
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