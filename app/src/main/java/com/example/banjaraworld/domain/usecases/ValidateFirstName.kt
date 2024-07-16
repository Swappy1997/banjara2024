package com.example.banjaraworld.domain.usecases

class ValidateFirstName {

    operator fun invoke(firstName: String): ValidationResult {

        if (firstName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please enter your first name"
            )
        }
        val containsLettersAndDigits = firstName.any {
            it.isDigit()
        }
        if (containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "First name should not contains number"
            )
        }
        return ValidationResult(successful = true)
    }
}