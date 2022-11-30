package com.github.adamr22.weatherapp.di

import android.content.Context
import com.github.adamr22.weatherapp.common.Constants
import com.github.adamr22.weatherapp.data.remote.NetworkService
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun providesRetrofit() = Retrofit
        .Builder()
        .baseUrl(Constants.baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesRetrofitService(retrofit: Retrofit) = retrofit.create(NetworkService::class.java)

}