package com.globa.prodmir.data.login.internal.token

import com.globa.prodmir.common.di.IoDispatcher
import com.globa.prodmir.data.login.api.model.ValidateResponse
import com.globa.prodmir.network.api.LoginApi
import com.globa.prodmir.network.api.model.Token
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class NetworkTokenValidator @Inject constructor(
    private val api: LoginApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun validate(token: String): ValidateResponse = withContext(dispatcher) {
        val response = api.validateToken(Token(token = token))
        when (response.code()) {
            200 -> Gson().fromJson(response.body().toString(), ValidateResponse.Valid::class.java)
            204 -> Gson().fromJson(response.body().toString(), ValidateResponse.UserNotFound::class.java)
            403 -> Gson().fromJson(response.body().toString(), ValidateResponse.Outdated::class.java)
            else -> ValidateResponse.Error
        }
    }
}