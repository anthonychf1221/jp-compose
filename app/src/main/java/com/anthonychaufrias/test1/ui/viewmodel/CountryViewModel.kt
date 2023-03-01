package com.anthonychaufrias.test1.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthonychaufrias.test1.data.CountryRepository
import com.anthonychaufrias.test1.data.model.CountryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val repository: CountryRepository
) : ViewModel(){

    private val _countries = MutableLiveData<List<CountryModel>>()
    val countries: LiveData<List<CountryModel>> = _countries

    private val _selectedCountry = MutableLiveData<CountryModel>()
    val selectedCountry: LiveData<CountryModel> = _selectedCountry

    private val _isExpanded = MutableLiveData<Boolean>()
    val isExpanded: LiveData<Boolean> = _isExpanded

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init{
        loadCountryList()
    }

    fun loadCountryList(){
        viewModelScope.launch {
            _isLoading.postValue(true)
            val list = repository.getCountryList()

            if( list.isNotEmpty() ){
                _selectedCountry.postValue(list[0])
            }

            _countries.postValue(list)
            _isLoading.postValue(false)
        }
    }

    fun onCountryChanged(country: CountryModel){
        onExpandedChanged(false)
        _selectedCountry.value = country
    }

    fun onExpandedChanged(expanded: Boolean){
        _isExpanded.value = expanded
    }


}