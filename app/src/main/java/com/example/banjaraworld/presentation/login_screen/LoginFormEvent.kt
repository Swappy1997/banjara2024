package com.example.banjaraworld.presentation.login_screen

sealed class LoginFormEvent {
    data class MobileChanged(val mobileNumber: String) : LoginFormEvent()
    data class FirstNameChanged(val firstName: String) : LoginFormEvent()
    data class CheckBoxChanged(val isChecked :Boolean):LoginFormEvent()
    object Continue :LoginFormEvent()
}