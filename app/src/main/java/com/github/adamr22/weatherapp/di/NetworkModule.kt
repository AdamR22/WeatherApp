package com.github.adamr22.weatherapp.di

import com.github.adamr22.weatherapp.data.remote.NetworkRepositoryImpl
import com.github.adamr22.weatherapp.domain.remote.NetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    @Singleton
    abstract fun providesNetworkRepository(networkRepo: NetworkRepositoryImpl): NetworkRepository

}