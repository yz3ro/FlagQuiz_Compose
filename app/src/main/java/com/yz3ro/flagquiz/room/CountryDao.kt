package com.yz3ro.flagquiz.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yz3ro.flagquiz.data.model.Country

@Dao
interface CountryDao {
    @Insert
    suspend fun insertCountry(country: List<Country>)

    @Query("SELECT COUNT(*) FROM flags")
    fun getCount(): LiveData<Int>

    @Query("SELECT * FROM flags ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomCountry(): Country?

    @Query("SELECT * FROM flags WHERE flag_name != :selectedCountryName ORDER BY RANDOM() LIMIT 3")
    suspend fun getRandomThreeCountryName(selectedCountryName: String) : Country?
}