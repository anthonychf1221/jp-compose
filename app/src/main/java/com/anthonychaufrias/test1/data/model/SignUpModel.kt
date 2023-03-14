package com.anthonychaufrias.test1.data.model

import com.anthonychaufrias.test1.domain.model.SignUp
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SignUpModel(
    @Expose @SerializedName("fullName") val fullName: String,
    @Expose @SerializedName("documentID") val documentID: String,
    @Expose @SerializedName("email") val email: String,
    @Expose @SerializedName("company") val company: String,
    @Expose @SerializedName("countryID") val countryID: Int
)

fun SignUp.toAPIModel() = SignUpModel(fullName, documentID, email, company, countryID)