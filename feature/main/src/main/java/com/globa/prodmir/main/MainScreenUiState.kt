package com.globa.prodmir.main

sealed class MainScreenUiState {
    object Loading: MainScreenUiState()
    data class LoggedIn(val token: String): MainScreenUiState()
    object LoggedOut: MainScreenUiState()
}
