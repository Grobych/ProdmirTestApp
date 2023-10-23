package com.globa.prodmir.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.globa.prodmir.login.internal.ErrorComposable
import com.globa.prodmir.login.internal.PhoneNumberComposable
import com.globa.prodmir.login.internal.SMSScreenComposable

@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel = hiltViewModel(),
    navigateToMain: () -> Unit
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
    val onSMSCodeChange = fun (code: String) {
        viewModel.onSMSCodeChange(code)
    }
    val onSendSMSCodeButtonClick = fun() {
        viewModel.onSendSMSCodeButtonClick()
    }
    val onRequestNewSMSClick = fun() {
        viewModel.onRequestNewSMSClick()
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
        is LoginScreenUiState.SMS -> {
            SMSScreenComposable(
                phoneNumber = state.phoneNumber,
                smsCode = state.code,
                timeout = state.timeout,
                onSmsCodeChange = onSMSCodeChange,
                onContinueButtonClick = onSendSMSCodeButtonClick,
                onNewSmsRequestClick = onRequestNewSMSClick
            )
        }
        is LoginScreenUiState.Error -> {
            ErrorComposable(
                message = state.message,
                onReturnButtonClick = {viewModel.onErrorReturnButtonClick()}
            )
        }
        LoginScreenUiState.Authorized -> {
            navigateToMain()
        }
        LoginScreenUiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}