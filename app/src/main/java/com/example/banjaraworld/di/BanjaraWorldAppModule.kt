package com.example.banjaraworld.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.banjaraworld.BuildConfig
import com.example.banjaraworld.common.ConnectivityObserver
import com.example.banjaraworld.common.NetworkConnectivityObserver
import com.example.banjaraworld.common.utils.BwConstants.BASE_URL
import com.example.banjaraworld.data.BanjaraWorldApi
import com.example.banjaraworld.data.repository.LoginRepositoryImpl
import com.example.banjaraworld.domain.repository.LoginRepository
import com.example.banjaraworld.domain.usecases.ValidateCheckBox
import com.example.banjaraworld.domain.usecases.ValidateFirstName
import com.example.banjaraworld.domain.usecases.ValidateMobileNumber
import com.example.banjaraworld.domain.usecases.ValidateOtp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object BanjaraWorldAppModule {


    @Provides
    fun ProvideValidateMobile(): ValidateMobileNumber {
        return ValidateMobileNumber()
    }

    @Provides
    fun ProvideValidateName(): ValidateFirstName {
        return ValidateFirstName()
    }

    @Provides
    fun ProvidesValidateCheckBox(): ValidateCheckBox {
        return ValidateCheckBox()
    }

    @Provides
    fun ProvideValideOtp(): ValidateOtp {
        return ValidateOtp()
    }

    @Singleton
    @Provides
    fun provideNetworkConnectivityObserver(
        @ApplicationContext applicationContext: Context
    ): ConnectivityObserver {
        return NetworkConnectivityObserver(applicationContext)
    }

    @Singleton
    @Provides
    fun provideLoginRepository(api: BanjaraWorldApi): LoginRepository {
        return LoginRepositoryImpl(api)
    }


    @Singleton
    @Provides
    fun provideLoginApi(): BanjaraWorldApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BanjaraWorldApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()
        okHttpClient.addInterceptor(httpLoggingInterceptor)
        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(chuckerInterceptor)
        }
        return okHttpClient.build()
    }


    @Provides
    @Singleton
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideRequestHeaderInterceptor(
    ): RequestHeaderInterceptor {
        return RequestHeaderInterceptor()
    }

}