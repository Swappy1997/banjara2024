package com.example.banjaraworld.presentation.marriageregistration.secondscreenmarriage

sealed class MarriageSecondScreenEvent {
    data class FirstNameChanged(val firstName: String) : MarriageSecondScreenEvent()
    data class MiddleNameChanged(val middleName: String) : MarriageSecondScreenEvent()
    data class LastNameChanged(val lastName: String) : MarriageSecondScreenEvent()
    data class MotherNameChanged(val motherName: String) : MarriageSecondScreenEvent()
    object Continue : MarriageSecondScreenEvent()
}