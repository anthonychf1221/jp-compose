package com.anthonychaufrias.test1.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CountryModel (
    @Expose @SerializedName("idPais") val id: Int,
    @Expose @SerializedName("nombre") val name: String
)