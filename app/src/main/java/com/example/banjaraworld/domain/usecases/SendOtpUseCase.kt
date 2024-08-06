package com.example.banjaraworld.domain.usecases

import android.util.Log
import coil.network.HttpException
import com.example.banjaraworld.common.Resource
import com.example.banjaraworld.data.dto.SendOtpRequest
import com.example.banjaraworld.data.dto.SendOtpResponse
import com.example.banjaraworld.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import org.apache.http.params.HttpParams
import javax.inject.Inject

class SendOtpUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend operator fun invoke(sendOtpRequest: SendOtpRequest): Flow<Resource<SendOtpResponse>> =
        flow {
            try {
                emit(Resource.Loading())
                Log.d("TAG", "invoke: $sendOtpRequest")
                val sendOtpResponse = loginRepository.sendOtp(sendOtpRequest)
                emit(Resource.Success(sendOtpResponse))
            } catch (e: HttpException) {
                Log.e("SendOtpUseCase", "HttpException: ${e.message}")
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            } catch (e: IOException) {
                Log.e("SendOtpUseCase", "IOException: ${e.message}")
                emit(Resource.Error("Couldn't reach server. Check your internet connection"))
            }
        }

}