package com.globa.prodmir.network.api

import com.globa.prodmir.network.api.model.LoginRequest
import com.globa.prodmir.network.api.model.Token
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginApi {
    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<JsonElement>

    @POST("validate")
    fun validateToken(@Body token: Token): Call<JsonElement>

    @GET("logout")
    fun logout(): Call<JsonElement>
}