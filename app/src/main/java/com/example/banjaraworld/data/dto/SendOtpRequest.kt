package com.example.banjaraworld.data.dto

data class SendOtpRequest(
    val fcm_token: String,
    val mobile: String,
    val name: String
)