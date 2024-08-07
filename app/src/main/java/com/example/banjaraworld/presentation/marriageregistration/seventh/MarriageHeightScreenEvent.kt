package com.example.banjaraworld.presentation.marriageregistration.seventh

sealed class MarriageHeightScreenEvent {
    data class HeightChanged(val height: Int, val selectedUnit: String) :
        MarriageHeightScreenEvent()

    object Continue : MarriageHeightScreenEvent()

}