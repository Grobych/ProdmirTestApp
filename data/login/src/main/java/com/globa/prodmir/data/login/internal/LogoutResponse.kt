package com.globa.prodmir.data.login.internal

sealed class LogoutResponse {
    object LogoutSuccess: LogoutResponse()
    object AlreadyLogout: LogoutResponse()
    object UserNotFound: LogoutResponse()
}
