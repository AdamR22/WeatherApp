package com.github.adamr22.weatherapp.di

import android.content.Context
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesContext(@ApplicationContext context: Context) = context

    @Provides
    @Singleton
    fun providesFusedLocationClient(context: Context) =
        LocationServices.getFusedLocationProviderClient(context)

}