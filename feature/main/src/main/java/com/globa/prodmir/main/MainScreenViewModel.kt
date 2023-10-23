package com.globa.prodmir.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globa.prodmir.data.login.api.LoginRepository
import com.globa.prodmir.data.login.api.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val loginRepository: LoginRepository
): ViewModel()  {
    private val _uiState = MutableStateFlow<MainScreenUiState>(MainScreenUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchToken()
    }

    private fun fetchToken(){
        viewModelScope.launch {
            _uiState.value = MainScreenUiState.LoggedIn(tokenRepository.getToken())
        }
    }

    fun logout() {
        viewModelScope.launch {
            loginRepository.logout()
            tokenRepository.saveToken("")
            _uiState.value = MainScreenUiState.LoggedOut
        }
    }
}