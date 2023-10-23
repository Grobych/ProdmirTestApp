package com.globa.prodmir.network.api.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("phone") val phoneNumber: String,
    @SerializedName("check") val smsCode: Int? = null,
    @SerializedName("device") val deviceModel: String
)
