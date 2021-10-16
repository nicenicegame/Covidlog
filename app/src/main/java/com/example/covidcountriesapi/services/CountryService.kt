package com.example.covidcountriesapi.services

import com.example.covidcountriesapi.models.Country
import retrofit2.Call
import retrofit2.http.GET

interface CountryService {

    @GET("countries")
    fun getAffectedCountryList() : Call<List<Country>>
}