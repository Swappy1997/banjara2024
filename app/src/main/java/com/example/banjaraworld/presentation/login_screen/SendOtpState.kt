package com.example.banjaraworld.presentation.login_screen

import com.example.banjaraworld.data.dto.SendOtpResponse

data class SendOtpState(
    val isLoading :Boolean =false,
    val sendOtpResponse: SendOtpResponse? =null,
    val error :String =""
)
