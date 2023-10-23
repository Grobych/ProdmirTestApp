package com.globa.prodmir.network.api

import com.globa.prodmir.network.api.model.LoginRequest
import com.globa.prodmir.network.api.model.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginApi {
    @POST("/login")
    fun login(@Body loginRequest: LoginRequest): Response<out Any>

    @POST("/validate")
    fun validateToken(@Body token: Token): Response<out Any>

    @GET("/logout")
    fun logout(): Response<out Any>
}