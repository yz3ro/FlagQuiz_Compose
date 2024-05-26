package com.yz3ro.flagquiz

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

    private val _randomCountryName = MutableLiveData<Country?>()
    val randomCountryName: LiveData<Country?> = _randomCountryName

    fun getRandomCountryName(selectedCountryName: String){
        viewModelScope.launch {
            _randomCountryName.value = repository.getRandomCountryName(selectedCountryName)
        }
    }
}