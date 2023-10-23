package com.globa.prodmir.data.login.api.model

sealed class LogoutResponse {
    object LogoutSuccess: LogoutResponse()
    object AlreadyLogout: LogoutResponse()
    object UserNotFound: LogoutResponse()
}
