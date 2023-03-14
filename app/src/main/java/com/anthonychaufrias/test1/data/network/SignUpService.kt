package com.anthonychaufrias.test1.data.network

import com.anthonychaufrias.test1.data.model.SignUpModel
import com.anthonychaufrias.test1.data.model.SignUpResponseModel
import com.anthonychaufrias.test1.util.Resource
import javax.inject.Inject

class SignUpService @Inject constructor(
    private val api: SignUpAPIClient
): BaseService(){

    suspend fun signUp(signUp: SignUpModel): Resource<SignUpResponseModel> {
        return safeApiCall {
            api.signUp(signUp)
        }
    }

}