package com.yz3ro.flagquiz.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yz3ro.flagquiz.data.model.Country

@Database(entities = [Country::class], version = 1)
abstract class Databases :RoomDatabase() {
    abstract fun getCountryDao() : CountryDao
}