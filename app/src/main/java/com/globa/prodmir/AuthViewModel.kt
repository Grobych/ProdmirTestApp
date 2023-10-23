package com.globa.prodmir

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globa.prodmir.data.login.api.TokenRepository
import com.globa.prodmir.data.login.api.model.ValidateResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val tokenRepository: TokenRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        checkToken()
    }

    private fun checkToken() {
        viewModelScope.launch {
            val token = tokenRepository.getToken()
            if (token.isEmpty()) _uiState.value = AuthUiState.NotAuthorized
            else {
                when (tokenRepository.verifyToken(token)) {
                    is ValidateResponse.Valid -> _uiState.value = AuthUiState.Authorized
                    else -> _uiState.value = AuthUiState.NotAuthorized
                }
            }
        }

    }
}