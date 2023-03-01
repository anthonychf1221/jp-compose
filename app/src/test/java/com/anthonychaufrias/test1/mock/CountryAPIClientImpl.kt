package com.anthonychaufrias.test1.mock

import com.anthonychaufrias.test1.data.model.CountryModel
import com.anthonychaufrias.test1.data.model.ResponseModel
import com.anthonychaufrias.test1.data.network.CountryAPIClient
import retrofit2.Response

class CountryAPIClientImpl: CountryAPIClient {

    override suspend fun getCountries(): Response<ResponseModel> {
        var list = mutableListOf<CountryModel>()
        list.add( CountryModel(1, "Perú") )
        list.add( CountryModel(2, "EE.UU") )
        list.add( CountryModel(3, "Colombia") )

        val listResponse = ResponseModel("Ok", list)
        return Response.success(listResponse)
    }

}