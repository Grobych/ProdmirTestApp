package com.globa.prodmir.data.login.api

import com.globa.prodmir.data.login.api.model.LoginResponse
import com.globa.prodmir.data.login.api.model.LogoutResponse

interface LoginRepository {
    suspend fun login(phoneNumber: String, deviceModel: String, code: Int? = null): LoginResponse
    suspend fun logout(): LogoutResponse
}