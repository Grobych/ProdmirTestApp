package com.globa.prodmir.data.login.api.model

import com.google.gson.annotations.SerializedName

sealed class ValidateResponse{
    data class Valid(
        @SerializedName("status") val code: Int,
        @SerializedName("message") val message: String,
        @SerializedName("userId") val userId: String,
        @SerializedName("phone") val phoneNumber: String
    ): ValidateResponse()
    data class Outdated(
        @SerializedName("status") val code: Int,
        @SerializedName("error") val message: String,
        @SerializedName("userId") val userId: String,
        @SerializedName("phone") val phoneNumber: String
    ): ValidateResponse()
    data class UserNotFound(
        @SerializedName("status") val code: Int,
        @SerializedName("error") val message: String,
        @SerializedName("userId") val userId: String?,
    ): ValidateResponse()
    object Error: ValidateResponse()
}
