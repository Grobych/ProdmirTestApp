package com.globa.prodmir.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(

): ViewModel() {
    private val _uiState = MutableStateFlow<LoginScreenUiState>(LoginScreenUiState.PhoneNumber())
    val uiState = _uiState.asStateFlow()
}