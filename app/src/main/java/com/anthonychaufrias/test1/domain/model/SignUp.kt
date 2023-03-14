package com.anthonychaufrias.test1.domain.model

import com.anthonychaufrias.test1.data.model.SignUpModel

data class SignUp(
    val fullName: String,
    val documentID: String,
    val email: String,
    val company: String,
    val countryID: Int
){
    companion object{
        fun emptyObject() = SignUp("", "", "", "", 1)
    }
}

fun SignUpModel.toDomain() = SignUp(fullName, documentID, email, company, countryID)