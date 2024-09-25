package com.example.banjaraworld.presentation.marriageregistration.firstscreenmarriage

data class MarriageFirstScreenState(
    val isGenderSelected: String = "",
    val isGenderSelectedError: String? = null,
    val isMarriageStatusSelectedError: String? = null,
    val isProfileCreatedForSelectedError: String? = null,
    val isMarriageStatusSelected: String = "",
    val isProfileCreatedForSelected: String = "",
    val isDietPreferenceSelected: String = "",
    val isDietPreferenceSelectedError: String? = null,
    val isBirthTimeSelected: String = "",
    val isBirthTimeSelectedError: String? = null,
    val isBirthDateSelected: String = "",
    val isBirthDateSelectedError: String? = null
)
