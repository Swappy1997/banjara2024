package com.example.banjaraworld.domain.repository

import com.example.banjaraworld.data.dto.SendOtpRequest
import com.example.banjaraworld.data.dto.SendOtpResponse

interface LoginRepository {

    suspend fun sendOtp(sendOtpRequest: SendOtpRequest): SendOtpResponse


}