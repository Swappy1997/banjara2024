package com.example.banjaraworld.domain.usecases.marriageregistration.marriagefirstscreen

import com.example.banjaraworld.domain.usecases.ValidationResult
import javax.inject.Inject

class ValidateIsGenderSelectedUseCase @Inject constructor() {

    operator fun invoke(gender: String): ValidationResult {

        if (gender.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please select your gender"
            )
        }
        return ValidationResult(successful = true)
    }
}