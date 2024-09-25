package com.example.banjaraworld.domain.usecases.marriageregistration.marriagefirstscreen

import com.example.banjaraworld.domain.usecases.ValidationResult
import javax.inject.Inject

class ValidateIsDietPreferencesSelectedUseCase @Inject constructor() {

    operator fun invoke(dietPreferences: String): ValidationResult {
        if (dietPreferences.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please select your diet preferences"
            )
        }
        return ValidationResult(successful = true)
    }
}