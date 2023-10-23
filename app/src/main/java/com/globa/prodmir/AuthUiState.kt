package com.globa.prodmir

sealed class AuthUiState {
    object Loading: AuthUiState()
    object Authorized: AuthUiState()
    object NotAuthorized: AuthUiState()
}
