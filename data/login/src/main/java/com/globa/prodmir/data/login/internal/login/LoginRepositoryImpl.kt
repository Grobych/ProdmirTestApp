package com.globa.prodmir.data.login.internal.login

import com.globa.prodmir.data.login.api.LoginRepository
import com.globa.prodmir.data.login.api.model.LoginResponse
import com.globa.prodmir.data.login.api.model.LogoutResponse
import javax.inject.Inject

internal class LoginRepositoryImpl @Inject constructor(
    private val loginNetworkDataSource: LoginNetworkDataSource
): LoginRepository {
    override suspend fun login(
        phoneNumber: String,
        deviceModel: String,
        code: Int?
    ): LoginResponse = loginNetworkDataSource
        .login(
            phoneNumber = phoneNumber,
            phoneModel = deviceModel,
            code = code
        )

    override suspend fun logout(): LogoutResponse = loginNetworkDataSource.logout()
}