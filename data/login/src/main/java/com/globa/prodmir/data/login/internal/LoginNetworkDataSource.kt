package com.globa.prodmir.data.login.internal

import com.globa.prodmir.common.di.IoDispatcher
import com.globa.prodmir.network.api.LoginApi
import com.globa.prodmir.network.api.model.LoginRequest
import com.globa.prodmir.network.api.model.Token
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginNetworkDataSource @Inject constructor(
    private val api: LoginApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun login(
        phoneNumber: String,
        phoneModel: String,
        code: Int? = null
    ): LoginResponse = withContext(dispatcher) {
        val response = api.login(LoginRequest(
            phoneNumber = phoneNumber,
            deviceModel = phoneModel,
            smsCode = code
        ))
        when (response.code()) {
            201 -> Gson().fromJson(response.body().toString(), LoginResponse.SMSSend::class.java)
            202 -> Gson().fromJson(response.body().toString(), LoginResponse.SMSChecked::class.java)
            206 -> LoginResponse.Error(code = response.code(), message = "Phone number has not been registered!")
            207 -> LoginResponse.Error(code = response.code(), message = "Unauthorized device!")
            else -> Gson().fromJson(response.body().toString(), LoginResponse.Error::class.java)
        }
    }

    suspend fun logout() = withContext(dispatcher) {
        val response = api.logout()
        when (response.code()) {
            204 -> LogoutResponse.LogoutSuccess
            403 -> LogoutResponse.AlreadyLogout
            404 -> LogoutResponse.UserNotFound
            else -> LogoutResponse.UserNotFound //???
        }
    }

    suspend fun validate(token: String): VerifyingResponse = withContext(dispatcher) {
        val response = api.validateToken(Token(token = token))
        when (response.code()) {
            200 -> Gson().fromJson(response.body().toString(), VerifyingResponse.Valid::class.java)
            204 -> Gson().fromJson(response.body().toString(), VerifyingResponse.UserNotFound::class.java)
            403 -> Gson().fromJson(response.body().toString(), VerifyingResponse.InValid::class.java)
            else -> VerifyingResponse.Error
        }
    }
}