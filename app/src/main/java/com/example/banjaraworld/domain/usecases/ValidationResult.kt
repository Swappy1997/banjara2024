package com.example.banjaraworld.domain.usecases

data class ValidationResult(
    val successful :Boolean,
    val errorMessage :String?=null
)
