package com.anthonychaufrias.test1.data.network

import com.anthonychaufrias.test1.data.model.CountryModel
import com.anthonychaufrias.test1.data.model.ListResponseModel
import com.anthonychaufrias.test1.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CountryService @Inject constructor(
    private val api: CountryAPIClient
): BaseService(){

    suspend fun getCountries(): Resource<ListResponseModel> {
        return safeApiCall {
            api.getCountries()
        }
    }

    /*suspend fun getCountries(): List<CountryModel> {
        return withContext(Dispatchers.IO){
            try {
                val response = api.getCountries()
                response.body()?.results ?: listOf<CountryModel>()
            }
            catch (e: Exception){
                listOf<CountryModel>()
            }
        }
    }*/

}