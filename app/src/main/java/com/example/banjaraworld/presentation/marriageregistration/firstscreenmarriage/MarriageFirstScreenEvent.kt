package com.example.banjaraworld.presentation.marriageregistration.firstscreenmarriage

sealed class MarriageFirstScreenEvent {
    data class GenderChanged(val gender: String) : MarriageFirstScreenEvent()
    data class ProfileCreatedForChanged(val profileCreatedFor: String) : MarriageFirstScreenEvent()
    data class MarriageStatusChanged(val marriageStatus: String) : MarriageFirstScreenEvent()
    object Continue : MarriageFirstScreenEvent()
}