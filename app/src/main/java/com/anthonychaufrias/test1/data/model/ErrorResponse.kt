package com.anthonychaufrias.test1.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorResponse (
    @Expose @SerializedName("status") val status: String,
    @Expose @SerializedName("code") val code: String,
    @Expose @SerializedName("message") val message: String
)