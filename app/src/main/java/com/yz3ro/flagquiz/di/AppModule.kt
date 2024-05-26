package com.yz3ro.flagquiz.di

import android.content.Context
import androidx.room.Room
import com.yz3ro.flagquiz.data.repo.FlagRepository
import com.yz3ro.flagquiz.data.retrofit.FlagAPI
import com.yz3ro.flagquiz.room.CountryDao
import com.yz3ro.flagquiz.room.Databases
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


    @Singleton
    @Provides
    fun provideCountryDao(@ApplicationContext context: Context): CountryDao {
        val db = Room.databaseBuilder(context, Databases::class.java, "flagquiz.sqlite")
            .createFromAsset("flagquiz.sqlite").build()
        return db.getCountryDao()
    }

    @Singleton
    @Provides
    fun provideFlagRepository(apiService : FlagAPI,countryDao: CountryDao): FlagRepository{
        return FlagRepository(apiService,countryDao)
    }


}