package com.example.banjaraworld.domain.usecases

class ValidateCheckBox {

    operator fun invoke(isCheck: Boolean): ValidationResult {

        if (!isCheck) {
            return ValidationResult(
                successful = false,
                errorMessage = "please accept the privacy policy and terms and condition"
            )
        }
        return ValidationResult(successful = true)
    }
}