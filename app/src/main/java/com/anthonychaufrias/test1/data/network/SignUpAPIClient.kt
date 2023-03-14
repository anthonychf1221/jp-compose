package com.anthonychaufrias.test1.data.network

import com.anthonychaufrias.test1.data.model.SignUpModel
import com.anthonychaufrias.test1.data.model.SignUpResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpAPIClient {

    @POST("signup")
    suspend fun signUp(
        @Body signUp: SignUpModel
    ): Response<SignUpResponseModel>

}