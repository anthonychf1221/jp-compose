package com.anthonychaufrias.test1.domain.model

import com.anthonychaufrias.test1.data.model.SignUpResponseModel

data class SignUpResponse (
    val status: String,
    val result: SignUp,
    val message: String
)

fun SignUpResponseModel.toDomain(signUp: SignUp) = SignUpResponse(status, signUp, message)