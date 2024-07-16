package com.example.banjaraworld.presentation.verfication_screen

sealed class VerificationOtpEvent {
    data class OptChanged(val otpValue: String) : VerificationOtpEvent()
    object Verify : VerificationOtpEvent()
}