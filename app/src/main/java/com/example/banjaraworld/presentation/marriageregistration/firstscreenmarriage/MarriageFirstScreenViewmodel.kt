package com.example.banjaraworld.presentation.marriageregistration.firstscreenmarriage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banjaraworld.domain.usecases.marriageregistration.marriagefirstscreen.ValidateIsBirthTimeSelectedUseCase
import com.example.banjaraworld.domain.usecases.marriageregistration.marriagefirstscreen.ValidateIsDateSelectedUseCase
import com.example.banjaraworld.domain.usecases.marriageregistration.marriagefirstscreen.ValidateIsDietPreferencesSelectedUseCase
import com.example.banjaraworld.domain.usecases.marriageregistration.marriagefirstscreen.ValidateIsGenderSelectedUseCase
import com.example.banjaraworld.domain.usecases.marriageregistration.marriagefirstscreen.ValidateIsMarriageStatusSelectedUseCase
import com.example.banjaraworld.domain.usecases.marriageregistration.marriagefirstscreen.ValidateIsProfileCreatedForUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarriageFirstScreenViewmodel @Inject constructor(
    private val validateIsGenderSelectedUseCase: ValidateIsGenderSelectedUseCase,
    private val validateIsMarriageStatusSelectedUseCase: ValidateIsMarriageStatusSelectedUseCase,
    private val validateIsProfileCreatedForUseCase: ValidateIsProfileCreatedForUseCase,
    private val validateIsDietPreferencesSelectedUseCase: ValidateIsDietPreferencesSelectedUseCase,
    private val validateIsBirthTimeSelectedUseCase: ValidateIsBirthTimeSelectedUseCase,
    private val validateIsDateSelectedUseCase: ValidateIsDateSelectedUseCase
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

            is MarriageFirstScreenEvent.MarriageDietPreference -> {
                state = state.copy(isDietPreferenceSelected = event.dietPreference)
            }

            is MarriageFirstScreenEvent.BirthDateChanged -> {
                state = state.copy(isBirthDateSelected = event.birthDate)
            }

            is MarriageFirstScreenEvent.BirthTimeChanged -> TODO()
        }
    }

    fun SubmitData() {
        val genderResult = validateIsGenderSelectedUseCase.invoke(state.isGenderSelected)
        val marriageStatusResult =
            validateIsMarriageStatusSelectedUseCase.invoke(state.isMarriageStatusSelected)
        val profileCreatedForResult =
            validateIsProfileCreatedForUseCase.invoke(state.isProfileCreatedForSelected)
        val dietPreferenceResult =
            validateIsDietPreferencesSelectedUseCase.invoke(state.isDietPreferenceSelected)
        val birthDateResult = validateIsDateSelectedUseCase.invoke(state.isBirthDateSelected)
        val hasError = listOf(
            genderResult,
            marriageStatusResult,
            profileCreatedForResult,
            dietPreferenceResult,
            birthDateResult
        )
            .any {
                !it.successful
            }
        if (hasError) {
            state = state.copy(
                isGenderSelectedError = genderResult.errorMessage,
                isMarriageStatusSelectedError = marriageStatusResult.errorMessage,
                isProfileCreatedForSelectedError = profileCreatedForResult.errorMessage,
                isDietPreferenceSelectedError = dietPreferenceResult.errorMessage,
                isBirthDateSelectedError = birthDateResult.errorMessage
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