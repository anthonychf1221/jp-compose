package com.anthonychaufrias.test1.data.network

import com.anthonychaufrias.test1.data.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface CountryAPIClient {

    @GET("paises")
    suspend fun getCountries(): Response<ResponseModel>

}