package com.globa.prodmir.network.api.model

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("token") val token: String
)
