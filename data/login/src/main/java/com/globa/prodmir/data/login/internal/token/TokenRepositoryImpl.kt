package com.globa.prodmir.data.login.internal.token

import com.globa.prodmir.data.login.api.TokenRepository
import com.globa.prodmir.data.login.api.model.ValidateResponse
import javax.inject.Inject

internal class TokenRepositoryImpl @Inject constructor(
    private val tokenLocalDataSource: TokenLocalDataSource,
    private val tokenValidator: NetworkTokenValidator
): TokenRepository {
    override suspend fun getToken(): String =
        tokenLocalDataSource.getAccessToken()

    override suspend fun saveToken(token: String) =
        tokenLocalDataSource.saveAccessToken(token = token)

    override suspend fun verifyToken(token: String): ValidateResponse =
        tokenValidator.validate(token = token)
}