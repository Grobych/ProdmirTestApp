package com.globa.prodmir.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    when(val state = uiState.value) {
        is LoginScreenUiState.Error -> TODO()
        is LoginScreenUiState.PhoneNumber -> TODO()
        is LoginScreenUiState.SMS -> TODO()
    }
}