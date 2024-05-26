package com.yz3ro.flagquiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yz3ro.flagquiz.data.model.Country
import com.yz3ro.flagquiz.data.repo.FlagRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val repository: FlagRepository
) : ViewModel() {
    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    init {
        checkAndFetchCountries()
    }
    private fun checkAndFetchCountries() {
        repository.getCountriesCount().observeForever { count ->
            if (count == 0) {
                fetchAndStoreCountries()
            } else {
                Timber.d("It's already in the database.")
            }
        }
    }
    private fun fetchAndStoreCountries() {
        viewModelScope.launch {
            try {
                val countries = repository.getAllCountries()
                val countryEntities = countries.map { country ->
                    Country(
                        flag_id = 0,
                        flag_name = country.translations.tur.common,
                        flag_url = country.flags.png,
                        flag_region = country.region
                    )
                }
                repository.insertCountries(countryEntities)
                Timber.d("The countries were registered in the database.")
                _countries.postValue(countryEntities)
            } catch (e: Exception) {
                Timber.e(e, "An error occurred while fetching or registering countries")
            }
        }
    }
}