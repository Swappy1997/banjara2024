package com.example.banjaraworld.presentation.marriageregistration.secondscreenmarriage

data class MarriageSecondScreenState(
    val firstName: String = "",
    val firstNameError: String? = null,
    val middleName: String = "",
    val middleNameError: String? = null,
    val lastName: String = "",
    val lastNameError: String? = null,
    val motherName: String = "",
    val motherNameError: String? = null
)
