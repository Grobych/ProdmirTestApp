package com.globa.prodmir.data.login.internal

import com.google.gson.annotations.SerializedName

sealed class VerifyingResponse{
    data class Valid(
        @SerializedName("status") val code: Int,
        @SerializedName("message") val message: String,
        @SerializedName("userId") val userId: String,
        @SerializedName("phone") val phoneNumber: String
    ): VerifyingResponse()
    data class InValid(
        @SerializedName("status") val code: Int,
        @SerializedName("error") val message: String,
        @SerializedName("userId") val userId: String,
        @SerializedName("phone") val phoneNumber: String
    ): VerifyingResponse()
    data class UserNotFound(
        @SerializedName("status") val code: Int,
        @SerializedName("error") val message: String,
        @SerializedName("userId") val userId: String?,
    ): VerifyingResponse()
    object Error: VerifyingResponse()
}
