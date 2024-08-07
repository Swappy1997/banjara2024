package com.example.banjaraworld.domain.usecases.marriageregistration.marriagesecondscreen

import com.example.banjaraworld.domain.usecases.ValidationResult
import javax.inject.Inject

class ValidateMiddleNameUseCase @Inject constructor() {

    operator fun invoke(firstName: String): ValidationResult {

        if (firstName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please enter your middle name"
            )
        }
        val containsLettersAndDigits = firstName.any {
            it.isDigit()
        }
        if (containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "Middle name should not contains number"
            )
        }
        return ValidationResult(successful = true)
    }
}