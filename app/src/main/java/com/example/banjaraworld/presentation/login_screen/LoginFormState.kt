package com.example.banjaraworld.presentation.login_screen

import com.example.banjaraworld.domain.usecases.ValidateMobileNumber

data class LoginFormState(
    val mobileNumber: String = "",
    val firstName: String = "",
    val checkBox: Boolean = false,
    val mobileNumberError: String? = "",
    val firstNameError: String? = "",
    val checkBoxError: String? = ""
)