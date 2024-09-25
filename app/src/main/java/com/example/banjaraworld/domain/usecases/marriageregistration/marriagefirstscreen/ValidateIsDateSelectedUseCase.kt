package com.example.banjaraworld.domain.usecases.marriageregistration.marriagefirstscreen

import com.example.banjaraworld.domain.usecases.ValidationResult
import javax.inject.Inject

class ValidateIsDateSelectedUseCase @Inject constructor() {

    operator fun invoke(isDateSelected: String): ValidationResult {

        if (isDateSelected.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please select your birth date"
            )
        }
        return ValidationResult(successful = true)
    }
}