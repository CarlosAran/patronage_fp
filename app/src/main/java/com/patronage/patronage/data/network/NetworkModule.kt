package com.patronage.patronage.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesJson() = Json {
        ignoreUnknownKeys = true
    }

    //TODO: Cambiar API chucknorris por API de preguntas
    @Singleton
    @Provides
    fun providesChuckNorrisAPI(json: Json): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.chucknorris.io/")
        .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
        .build()

    @Singleton
    @Provides
    fun providesChuckNorrisService(retrofit: Retrofit):ChuckNorrisService = retrofit.create(ChuckNorrisService::class.java)
}