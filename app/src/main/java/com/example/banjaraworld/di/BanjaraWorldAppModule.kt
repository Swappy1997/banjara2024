package com.example.banjaraworld.di

import android.content.Context
import com.example.banjaraworld.common.ConnectivityObserver
import com.example.banjaraworld.common.NetworkConnectivityObserver
import com.example.banjaraworld.domain.usecases.ValidateCheckBox
import com.example.banjaraworld.domain.usecases.ValidateFirstName
import com.example.banjaraworld.domain.usecases.ValidateMobileNumber
import com.example.banjaraworld.domain.usecases.ValidateOtp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
}