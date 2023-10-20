package com.globa.prodmir.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.globa.prodmir.login.internal.PhoneNumberComposable

@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    val onPhoneNumberChange = fun(number: String) {
        viewModel.onNumberChange(number)
    }
    val onAgreementCheckChange = fun(value: Boolean){
        viewModel.onAgreementChange(value)
    }
    val onContinueButtonClick = fun() {
        viewModel.sendPhoneNumber()
    }
    val onAgreementLinkClick = fun() {
        viewModel.onAgreementLinkClick()
    }
    val onAgreementRead = fun() {
        viewModel.onAgreementRead()
    }

    when(val state = uiState.value) {
        is LoginScreenUiState.PhoneNumber -> {
            PhoneNumberComposable(
                phoneNumber = state.phoneNumber,
                isPhoneNumberError = state.isWrongNumber,
                isAgreementRead = state.isAgreementRead,
                isAgreementAccepted = state.isAgreementAccept,
                onTextFieldChange = onPhoneNumberChange,
                onAgreementCheckButtonClick = onAgreementCheckChange,
                onContinueButtonClick = onContinueButtonClick,
                showAgreementDialog = state.showAgreementDialog,
                onLinkClicked =onAgreementLinkClick,
                onAgreementRead = onAgreementRead
            )
        }
        is LoginScreenUiState.SMS -> TODO()
        is LoginScreenUiState.Error -> TODO()
    }
}