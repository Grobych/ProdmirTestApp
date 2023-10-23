package com.globa.prodmir

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RootScreen(
    viewModel: AuthViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    when (uiState.value) {
        AuthUiState.Loading -> {} //TODO: add animation
        AuthUiState.Authorized -> NavController(startDestination = NavRoutes.Main)
        AuthUiState.NotAuthorized -> NavController(startDestination = NavRoutes.Login)
    }
}