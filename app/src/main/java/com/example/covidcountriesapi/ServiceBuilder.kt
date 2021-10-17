package com.example.covidcountriesapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val URL = "https://disease.sh/v2/"
    // Retrofit builder
    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> buildService(serviceType: Class<T>) : T {
        return retrofit.create(serviceType)
    }
}