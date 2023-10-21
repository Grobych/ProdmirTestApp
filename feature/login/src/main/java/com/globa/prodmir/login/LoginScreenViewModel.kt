package com.globa.prodmir.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(

): ViewModel() {
    private val _uiState = MutableStateFlow<LoginScreenUiState>(LoginScreenUiState.PhoneNumber())
    val uiState = _uiState.asStateFlow()

    private val phoneNumber = MutableStateFlow("")
    private val showAgreement = MutableStateFlow(false)
    private val isAgreementRead = MutableStateFlow(false)
    private val isAgreementAccept = MutableStateFlow(false)

    private val smsCode = MutableStateFlow("")
    init {
        phoneNumberInit()
        showAgreementInit()
        isAgreementReadInit()
        isAgreementAcceptInit()
        smsCodeInit()
    }
    private fun phoneNumberInit() {
        //TODO: add input check??
        phoneNumber.onEach { number ->
            val state = uiState.value
            if (state is LoginScreenUiState.PhoneNumber) {
                _uiState.update { state.copy(phoneNumber = number) }
            }
        }.launchIn(viewModelScope)
    }

    private fun showAgreementInit() {
        showAgreement.onEach {showAgreement ->
            val state = uiState.value
            if (state is LoginScreenUiState.PhoneNumber) {
                _uiState.update { state.copy(showAgreementDialog = showAgreement) }
            }
        }.launchIn(viewModelScope)
    }

    private fun isAgreementReadInit() {
        isAgreementRead.onEach {isRead ->
            val state = uiState.value
            if (state is LoginScreenUiState.PhoneNumber) {
                _uiState.update { state.copy(isAgreementRead = isRead) }
            }
        }.launchIn(viewModelScope)
    }

    private fun isAgreementAcceptInit() {
        isAgreementAccept.onEach {isAccept ->
            val state = uiState.value
            if (state is LoginScreenUiState.PhoneNumber) {
                _uiState.update { state.copy(isAgreementAccept = isAccept) }
            }
        }.launchIn(viewModelScope)
    }

    private fun smsCodeInit() {
        smsCode.onEach { code ->
            val state = uiState.value
            if (state is LoginScreenUiState.SMS) {
                _uiState.update { state.copy(code = code) }
            }
        }.launchIn(viewModelScope)
    }

    fun onNumberChange(number: String) {
        if (number.length <= 9)
            phoneNumber.value = number
    }

    fun onAgreementLinkClick() {
        showAgreement.value = true
    }

    fun onAgreementRead() {
        showAgreement.value = false
        isAgreementRead.value = true
    }

    fun onAgreementChange(value: Boolean) {
        isAgreementAccept.value = value
    }

    fun sendPhoneNumber() {
        //sendPhoneNumber to server
        //if ok response -->
        _uiState.value = LoginScreenUiState.SMS(phoneNumber = phoneNumber.value)
        // start sms request timeout
    }

    fun onSMSCodeChange(code: String) {
        if (code.length <= 6) smsCode.value = code
    }

    fun onSendSMSCodeButtonClick() {
        //TODO: send sms code to server
        //if ok response --> NavController.AuthViewModel -> navigate to main
    }

    fun onRequestNewSMSClick() {
        //TODO: end request to new sms
        smsCode.value = ""
        // timeout.restart
    }
}