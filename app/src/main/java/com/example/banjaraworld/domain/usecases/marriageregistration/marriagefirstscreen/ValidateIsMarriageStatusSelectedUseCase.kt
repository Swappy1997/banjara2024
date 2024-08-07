package com.example.banjaraworld.domain.usecases.marriageregistration.marriagefirstscreen

import com.example.banjaraworld.domain.usecases.ValidationResult
import javax.inject.Inject

class ValidateIsMarriageStatusSelectedUseCase @Inject constructor() {

    operator fun invoke(marriageStatus: String): ValidationResult {
        if (marriageStatus.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please select your marriage status"
            )
        }
        return ValidationResult(successful = true)
    }
}