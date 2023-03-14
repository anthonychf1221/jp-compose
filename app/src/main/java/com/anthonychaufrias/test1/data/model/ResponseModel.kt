package com.anthonychaufrias.test1.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListResponseModel (
    @Expose @SerializedName("status") val status: String,
    @Expose @SerializedName("results") val results: List<CountryModel>
)

data class SignUpResponseModel (
    @Expose @SerializedName("status") val status: String,
    @Expose @SerializedName("result") val result: SignUpModel,
    @Expose @SerializedName("message") val message: String
)