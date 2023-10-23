package com.globa.prodmir.data.login.api.model

import com.google.gson.annotations.SerializedName

sealed class LoginResponse {
    data class Error(
        @SerializedName("status") val code: Int,
        @SerializedName("message") val message: String
    ): LoginResponse()
    data class SMSSend(
        @SerializedName("status") val code: Int,
        @SerializedName("phone") val phoneNumber: String,
        @SerializedName("id") val id: String,
        @SerializedName("checkAttempt") val checkAttempt: Int,
        @SerializedName("updatedAt") val updatedAt: Long,
        @SerializedName("device") val device: String?,
        @SerializedName("message") val message: String?
    ): LoginResponse()
    data class SMSChecked(
        @SerializedName("status") val code: Int,
        @SerializedName("profile") val profile: UserProfile,
        @SerializedName("accessToken") val accessToken: String,
        @SerializedName("refreshToken") val refreshToken: String
    ): LoginResponse()
}

data class Address(
    @SerializedName("country") val country: String = "",
    @SerializedName("city") val city: String = "",
    @SerializedName("street") val street: String = "",
    @SerializedName("building") val building: String = "",
    @SerializedName("entrance") val entrance: String = "",
    @SerializedName("floor") val floor: String = "",
    @SerializedName("apartment") val apartment: String = "",
    @SerializedName("domophone") val domophone: String = ""
)

data class UserProfile(
    @SerializedName("id") val id: String,
    @SerializedName("phone") val phoneNumber: String,
    @SerializedName("firstName") val firstName: String = "",
    @SerializedName("middleName") val middleName: String = "",
    @SerializedName("lastName") val lastName: String = "",
    @SerializedName("email") val email: String = "",
    @SerializedName("verifiedEmail") val isEmailVerified: Boolean = false,
    @SerializedName("pushId") val pushId: String = "",
    @SerializedName("addresses") val addressList: List<Address>,
    @SerializedName("createdAt") val createdAt: Long,
    @SerializedName("updatedAt") val updatedAt: Long
)
