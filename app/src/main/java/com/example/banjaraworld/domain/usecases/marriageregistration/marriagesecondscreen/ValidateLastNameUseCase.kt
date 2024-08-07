package com.example.banjaraworld.domain.usecases.marriageregistration.marriagesecondscreen

import com.example.banjaraworld.domain.usecases.ValidationResult
import javax.inject.Inject

class ValidateLastNameUseCase @Inject constructor() {

    operator fun invoke(lastName: String): ValidationResult {

        if (lastName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please enter your last name"
            )
        }
        val containsLettersAndDigits = lastName.any {
            it.isDigit()
        }
        if (containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "Last name should not contains number"
            )
        }
        return ValidationResult(successful = true)
    }
}