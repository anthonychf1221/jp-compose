package com.anthonychaufrias.test1.data

import com.anthonychaufrias.test1.data.model.SignUpModel
import com.anthonychaufrias.test1.data.model.SignUpResponseModel
import com.anthonychaufrias.test1.data.network.SignUpService
import com.anthonychaufrias.test1.domain.model.SignUpResponse
import com.anthonychaufrias.test1.domain.model.toDomain
import com.anthonychaufrias.test1.util.Resource
import javax.inject.Inject

class SignUpRepository @Inject constructor(private val service: SignUpService) {

    suspend fun signUp(signUpModel: SignUpModel): Resource<SignUpResponse> {
        return when( val resource = service.signUp(signUpModel) ){
            is Resource.Success<*> -> {
                val signUp = (resource.data as SignUpResponseModel).result.toDomain()
                Resource.Success(resource.data.toDomain(signUp))
            }
            is Resource.Error<*> -> {
                Resource.Error(resource.message)
            }
        }
    }

}