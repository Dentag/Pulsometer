package com.dentag.pulsometer.datasources

import com.dentag.pulsometer.model.RecyclerData
import kotlinx.coroutines.flow.Flow

interface MeasureRemoteDataSource {
    suspend fun getMeasures(): Flow<List<RecyclerData.PressureAndPulse>>
    suspend fun postMeasure(measure: RecyclerData.PressureAndPulse)
}