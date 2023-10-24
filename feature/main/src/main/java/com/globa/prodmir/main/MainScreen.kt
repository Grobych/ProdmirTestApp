package com.globa.prodmir.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.globa.prodmir.common.composable.Header
import com.globa.prodmir.main.internal.LoggedInComposable

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
    closeApp: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()
    val onLogoutButtonClick = fun() {
        viewModel.logout()
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Header(
                text = stringResource(R.string.main),
                onBackButtonClick = closeApp
            )
        },
        content = {
            when(val state = uiState.value) {
                MainScreenUiState.Loading -> CircularProgressIndicator()
                is MainScreenUiState.LoggedIn ->
                    LoggedInComposable(
                        modifier = Modifier.padding(it),
                        token = state.token,
                        onLogoutButtonClick = onLogoutButtonClick
                    )
                MainScreenUiState.LoggedOut -> {
                    navigateToLogin()
                }
            }
        }
    )
}