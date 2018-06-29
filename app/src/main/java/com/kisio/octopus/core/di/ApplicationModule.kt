package com.kisio.octopus.core.di

import android.content.Context
import com.kisio.octopus.AndroidApplication
import com.kisio.octopus.BuildConfig
import com.kisio.octopus.features.connection.domain.ConnectionRepository
import com.kisio.octopus.features.create.domain.CreateRepository
import com.kisio.octopus.features.restaurant.domain.RestaurantRepository
import com.kisio.octopus.features.restaurants.domain.RestaurantsRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://www.rachik-abidi.com/octopus/ws/")
                .client(createClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRestaurantsRepository(dataSource: RestaurantsRepository.Network): RestaurantsRepository = dataSource

    @Provides
    @Singleton
    fun provideRestaurantRepository(dataSource: RestaurantRepository.Network): RestaurantRepository = dataSource

    @Provides
    @Singleton
    fun provideConnectionRepository(dataSource: ConnectionRepository.Network): ConnectionRepository = dataSource

    @Provides
    @Singleton
    fun provideCreateRepository(dataSource: CreateRepository.Network): CreateRepository = dataSource
}
