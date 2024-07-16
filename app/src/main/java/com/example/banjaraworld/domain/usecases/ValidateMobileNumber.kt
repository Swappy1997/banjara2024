package com.example.banjaraworld.domain.usecases

class ValidateMobileNumber {
    operator fun invoke(mobile: String): ValidationResult {
        if (mobile.length < 10) {
            return ValidationResult(
                successful = false,
                errorMessage = "mobile should be 10 digit"
            )
        }
        return ValidationResult(successful = true)
    }
}