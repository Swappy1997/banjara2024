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
    val isBirthTimeSelectedError: String? = null,
    val isBirthDateSelectedError: String? = null,
    var birthTime: String = "select time",
    var birthDate: String = "select date",
    var timeChipClick: Boolean = false,
    var dateChipClick: Boolean = false,
)
