package com.example.banjaraworld.domain.usecases.marriageregistration.marriagesecondscreen

import com.example.banjaraworld.domain.usecases.ValidationResult
import javax.inject.Inject

class ValidateMotherNameUseCase @Inject constructor() {

    operator fun invoke(motherName: String): ValidationResult {

        if (motherName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please enter your mother name"
            )
        }
        val containsLettersAndDigits = motherName.any {
            it.isDigit()
        }
        if (containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "Mother name should not contains number"
            )
        }
        return ValidationResult(successful = true)
    }
}