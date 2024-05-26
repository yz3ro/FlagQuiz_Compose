package com.yz3ro.flagquiz.data.retrofit

import com.yz3ro.flagquiz.data.model.CountryResponse
import retrofit2.http.GET

interface FlagAPI {
    @GET("independent?status=true")
    suspend fun getIndependentCountries(): List<CountryResponse>
}