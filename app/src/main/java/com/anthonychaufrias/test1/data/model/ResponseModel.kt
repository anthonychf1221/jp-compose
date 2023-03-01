package com.anthonychaufrias.test1.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseModel (
    @Expose @SerializedName("status") val status: String,
    @Expose @SerializedName("results") val results: List<CountryModel>
)