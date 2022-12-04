package com.github.adamr22.weatherapp.di

import com.github.adamr22.weatherapp.data.location.LocationRepositoryImpl
import com.github.adamr22.weatherapp.domain.location.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun providesLocationRepository(locationRepositoryImpl: LocationRepositoryImpl): LocationRepository

}