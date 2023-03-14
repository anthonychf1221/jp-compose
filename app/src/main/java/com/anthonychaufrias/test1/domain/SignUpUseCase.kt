package com.anthonychaufrias.test1.domain

import com.anthonychaufrias.test1.data.SignUpRepository
import com.anthonychaufrias.test1.data.model.toAPIModel
import com.anthonychaufrias.test1.domain.model.SignUp
import com.anthonychaufrias.test1.domain.model.SignUpResponse
import com.anthonychaufrias.test1.util.Resource
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: SignUpRepository
){

    suspend operator fun invoke(signUp: SignUp): Resource<SignUpResponse> {
        return repository.signUp(signUp.toAPIModel())
    }

}