package com.yz3ro.flagquiz.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.yz3ro.flagquiz.data.model.Country
import com.yz3ro.flagquiz.data.model.CountryResponse
import com.yz3ro.flagquiz.data.retrofit.FlagAPI
import com.yz3ro.flagquiz.room.CountryDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlagRepository @Inject constructor(
    private val apiService: FlagAPI,
    private val flagDao: CountryDao
) {
    suspend fun getAllCountries() = apiService.getIndependentCountries()

    suspend fun insertCountries(countries: List<Country>) = flagDao.insertCountry(countries)

    fun getCountriesCount(): LiveData<Int> = flagDao.getCount()

}