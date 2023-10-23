package com.globa.prodmir.data.login.api

import com.globa.prodmir.data.login.api.model.ValidateResponse

interface TokenRepository {
    suspend fun getToken(): String
    suspend fun saveToken(token: String)
    suspend fun verifyToken(token: String): ValidateResponse
}