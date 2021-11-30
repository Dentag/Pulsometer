package com.dentag.pulsometer.di

import com.dentag.pulsometer.datasources.FirestoreDataSource
import com.dentag.pulsometer.datasources.MeasureRemoteDataSource
import com.dentag.pulsometer.repos.MeasureRepository
import com.dentag.pulsometer.repos.MeasureRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface Binds {
    @Binds
    fun bindMeasureRepository(impl: MeasureRepositoryImpl): MeasureRepository

    @Binds
    fun bindMeasureRemoteDatasource(impl: FirestoreDataSource): MeasureRemoteDataSource
}