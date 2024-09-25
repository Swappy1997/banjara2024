package com.example.banjaraworld.presentation.marriageregistration.firstscreenmarriage

sealed class MarriageFirstScreenEvent {
    data class GenderChanged(val gender: String) : MarriageFirstScreenEvent()
    data class ProfileCreatedForChanged(val profileCreatedFor: String) : MarriageFirstScreenEvent()
    data class MarriageStatusChanged(val marriageStatus: String) : MarriageFirstScreenEvent()
    data class MarriageDietPreference(val dietPreference: String) : MarriageFirstScreenEvent()
    data class BirthDateChanged(val birthDate: String) : MarriageFirstScreenEvent()
    data class BirthTimeChanged(val birthTime: String) : MarriageFirstScreenEvent()
    object Continue : MarriageFirstScreenEvent()
}