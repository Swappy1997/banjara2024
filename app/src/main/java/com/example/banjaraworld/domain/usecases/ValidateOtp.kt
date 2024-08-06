package com.example.banjaraworld.domain.usecases

class ValidateOtp {

    operator fun invoke(otpValue: String): ValidationResult {

        if (otpValue.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please enter otp "
            )
        }
        if (otpValue.length < 6) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please enter 6 digit otp "
            )
        }
        return ValidationResult(successful = true)
    }
}