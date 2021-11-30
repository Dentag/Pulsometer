package com.dentag.pulsometer.model

sealed class RecyclerData {
    data class Date(val date: String) : RecyclerData()

    data class PressureAndPulse(
        val timeInMillis: Long = 0,
        val highPressure: Int = 0,
        val lowPressure: Int = 0,
        val pulse: Int = 0
    ) : RecyclerData()
}