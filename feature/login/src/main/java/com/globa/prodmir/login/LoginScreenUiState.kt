package com.globa.prodmir.login

sealed class LoginScreenUiState {
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
        val phoneNumber: String,
        val timeout: Int = 0): LoginScreenUiState()
}
