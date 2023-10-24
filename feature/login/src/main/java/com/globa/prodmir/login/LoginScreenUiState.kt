package com.globa.prodmir.login

sealed class LoginScreenUiState {
    object Loading: LoginScreenUiState()
    object Authorized: LoginScreenUiState()
    object Exit: LoginScreenUiState()
    data class Error(
        val code: Int,
        val message: String
    ): LoginScreenUiState()
    data class PhoneNumber(
        val phoneNumber: String = "",
        val isAgreementRead: Boolean = false,
        val isAgreementAccept: Boolean = false,
        val isWrongNumber: Boolean = false,
        val showAgreementDialog: Boolean = false
    ): LoginScreenUiState() //input error
    data class SMS(
        val code: String = "",
        val isSMSError: Boolean = false,
        val phoneNumber: String,
        val timeout: Int = 60): LoginScreenUiState()
}
