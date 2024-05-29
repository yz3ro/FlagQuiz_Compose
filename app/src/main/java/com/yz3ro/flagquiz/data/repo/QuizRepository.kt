package com.yz3ro.flagquiz.data.repo

import com.yz3ro.flagquiz.data.model.Country
import com.yz3ro.flagquiz.room.CountryDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizRepository @Inject constructor(
    private val countryDao : CountryDao
){
    suspend fun getRandomCountry(): Country? {
        return countryDao.getRandomCountry()
    }

    suspend fun getRandomCountryName(selectedCountryName: String) : List<Country>{
        return countryDao.getRandomThreeCountryName(selectedCountryName)
    }
}