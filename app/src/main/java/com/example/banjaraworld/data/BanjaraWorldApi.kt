package com.example.banjaraworld.data

import com.example.banjaraworld.common.Resource
import com.example.banjaraworld.data.dto.SendOtpRequest
import com.example.banjaraworld.data.dto.SendOtpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface BanjaraWorldApi {

    @POST("customer/sendOtp")
    suspend fun sendOtp(@Body sendOtpRequest: SendOtpRequest): SendOtpResponse
}