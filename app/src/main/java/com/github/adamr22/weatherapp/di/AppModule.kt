package com.github.adamr22.weatherapp.di

import android.app.Application
import android.content.Context
import com.github.adamr22.weatherapp.common.Constants
import com.github.adamr22.weatherapp.data.location.LocationRepositoryImpl
import com.github.adamr22.weatherapp.data.remote.NetworkService
import com.github.adamr22.weatherapp.domain.location.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesFusedLocationClient(context: Application) =
        LocationServices.getFusedLocationProviderClient(context)

    @Provides
    @Singleton
    fun providesRetrofit(): NetworkService {
        return Retrofit
            .Builder()
            .baseUrl(Constants.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

}