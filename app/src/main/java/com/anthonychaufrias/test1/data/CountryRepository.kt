package com.anthonychaufrias.test1.data

import com.anthonychaufrias.test1.data.model.ListResponseModel
import com.anthonychaufrias.test1.data.network.CountryService
import com.anthonychaufrias.test1.util.Resource
import javax.inject.Inject

class CountryRepository @Inject constructor(private val service: CountryService) {

    suspend fun getCountryList(): Resource<ListResponseModel> {
        return service.getCountries()
    }

}