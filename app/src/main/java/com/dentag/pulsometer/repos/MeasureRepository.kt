package com.dentag.pulsometer.repos

import com.dentag.pulsometer.model.RecyclerData
import kotlinx.coroutines.flow.Flow

interface MeasureRepository {
    suspend fun getMeasures(): Flow<List<RecyclerData.PressureAndPulse>>
    suspend fun postMeasure(measure: RecyclerData.PressureAndPulse)
}