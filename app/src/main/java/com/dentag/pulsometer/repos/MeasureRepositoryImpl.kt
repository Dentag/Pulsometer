package com.dentag.pulsometer.repos

import com.dentag.pulsometer.datasources.MeasureRemoteDataSource
import com.dentag.pulsometer.model.RecyclerData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MeasureRepositoryImpl @Inject constructor(
    private val remoteDatasource: MeasureRemoteDataSource
) : MeasureRepository {
    override suspend fun getMeasures(): Flow<List<RecyclerData.PressureAndPulse>> {
        return remoteDatasource.getMeasures()
    }

    override suspend fun postMeasure(measure: RecyclerData.PressureAndPulse) {
        remoteDatasource.postMeasure(measure)
    }
}