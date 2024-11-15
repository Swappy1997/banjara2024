package com.example.banjaraworld.domain.usecases.marriageregistration.marriagefirstscreen

import com.example.banjaraworld.domain.usecases.ValidationResult
import javax.inject.Inject

class ValidateIsProfileCreatedForUseCase @Inject constructor() {
    operator fun invoke(profileCreatedFor: String): ValidationResult {
        if (profileCreatedFor.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please select profile created for"
            )
        }
        return ValidationResult(successful = true)
    }
}