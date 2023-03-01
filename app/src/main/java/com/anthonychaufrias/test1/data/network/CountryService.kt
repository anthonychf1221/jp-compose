package com.anthonychaufrias.test1.data.network

import com.anthonychaufrias.test1.data.model.CountryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CountryService @Inject constructor(private val api: CountryAPIClient){

    suspend fun getCountries(): List<CountryModel> {
        return withContext(Dispatchers.IO){
            try {
                val response = api.getCountries()
                response.body()?.results ?: listOf<CountryModel>()
            }
            catch (e: Exception){
                listOf<CountryModel>()
            }
        }
    }

}