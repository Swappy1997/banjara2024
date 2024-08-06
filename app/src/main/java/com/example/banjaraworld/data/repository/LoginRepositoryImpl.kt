package com.example.banjaraworld.data.repository

import android.util.Log
import com.example.banjaraworld.data.BanjaraWorldApi
import com.example.banjaraworld.data.dto.SendOtpRequest
import com.example.banjaraworld.data.dto.SendOtpResponse
import com.example.banjaraworld.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val api : BanjaraWorldApi): LoginRepository {

    override suspend fun sendOtp(sendOtpRequest: SendOtpRequest): SendOtpResponse {
        Log.d("sendotp","sendOtp: $sendOtpRequest")
        return api.sendOtp(sendOtpRequest)
    }
}