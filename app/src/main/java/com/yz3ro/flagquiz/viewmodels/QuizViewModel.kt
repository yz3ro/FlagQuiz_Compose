package com.yz3ro.flagquiz.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yz3ro.flagquiz.data.model.Country
import com.yz3ro.flagquiz.data.repo.QuizRepository
import com.yz3ro.flagquiz.room.CountryDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val repository: QuizRepository
) : ViewModel() {
    private val _randomCountry = MutableLiveData<Country?>()
    val randomCountry: LiveData<Country?> = _randomCountry

    fun getRandomCountry() {
        viewModelScope.launch {
            _randomCountry.value = repository.getRandomCountry()
        }
    }

    private val _randomCountryNames = MutableLiveData<List<Country>>()
    val randomCountryNames: LiveData<List<Country>> = _randomCountryNames

    fun getRandomCountryName(selectedCountryName: String){
        viewModelScope.launch {
            _randomCountryNames.value = repository.getRandomCountryName(selectedCountryName)
        }
    }
}