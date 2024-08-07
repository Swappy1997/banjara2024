package com.example.banjaraworld.domain.usecases.marriageregistration.marriagesecondscreen

import com.example.banjaraworld.domain.usecases.ValidationResult
import javax.inject.Inject

class ValidateMarriageMiddleNameUseCase @Inject constructor() {

    operator fun invoke(middleName: String): ValidationResult {

        if (middleName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please enter your middle name"
            )
        }
        val containsLettersAndDigits = middleName.any {
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