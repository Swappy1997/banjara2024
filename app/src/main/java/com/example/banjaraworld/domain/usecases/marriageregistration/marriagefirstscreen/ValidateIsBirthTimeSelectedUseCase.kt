package com.example.banjaraworld.domain.usecases.marriageregistration.marriagefirstscreen

import com.example.banjaraworld.domain.usecases.ValidationResult
import javax.inject.Inject

class ValidateIsBirthTimeSelectedUseCase @Inject constructor() {


    operator fun invoke(isTimeSelected: String): ValidationResult {

        if (isTimeSelected.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please select your birth time"
            )
        }
        return ValidationResult(successful = true)
    }

}