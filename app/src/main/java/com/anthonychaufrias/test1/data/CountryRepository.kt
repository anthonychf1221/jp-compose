package com.anthonychaufrias.test1.data

import com.anthonychaufrias.test1.data.model.CountryModel
import com.anthonychaufrias.test1.data.network.CountryService
import javax.inject.Inject

class CountryRepository @Inject constructor(private val api: CountryService) {

    suspend fun getCountryList(): List<CountryModel> {
        return api.getCountries()
    }

}