package com.globa.prodmir.login

import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globa.prodmir.data.login.api.LoginRepository
import com.globa.prodmir.data.login.api.TokenRepository
import com.globa.prodmir.data.login.api.model.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val tokenRepository: TokenRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<LoginScreenUiState>(LoginScreenUiState.PhoneNumber())
    val uiState = _uiState.asStateFlow()

    private val phoneNumber = MutableStateFlow("")
    private val showAgreement = MutableStateFlow(false)
    private val isAgreementRead = MutableStateFlow(false)
    private val isAgreementAccept = MutableStateFlow(false)

    private val smsCode = MutableStateFlow("")
    private val timeout = MutableStateFlow(60)
    init {
        phoneNumberInit()
        showAgreementInit()
        isAgreementReadInit()
        isAgreementAcceptInit()
        smsCodeInit()
        timeoutInit()
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

    private fun timeoutInit() {
        timeout.onEach { timeout ->
            val state = uiState.value
            if (state is LoginScreenUiState.SMS) {
                _uiState.update { state.copy(timeout = timeout) }
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
        viewModelScope.launch {
            _uiState.value = LoginScreenUiState.Loading
            val response = loginRepository.login(
                phoneNumber = "375"+phoneNumber.value,
                deviceModel = Build.MODEL
            )
            when (response) {
                is LoginResponse.Error -> _uiState.value =
                    LoginScreenUiState.Error(code = response.code, message = response.message)
                is LoginResponse.SMSChecked ->
                    LoginScreenUiState.Error(code = 500, message = "code 202 is found, but expected code 201")
                is LoginResponse.SMSSend -> {
                    _uiState.value = LoginScreenUiState.SMS(phoneNumber = phoneNumber.value)
                    restartTimeout()
                }
            }
        }
    }

    fun onSMSCodeChange(code: String) {
        if (code.length <= 6) smsCode.value = code
    }

    fun onSendSMSCodeButtonClick() {
        viewModelScope.launch {
            val response = loginRepository.login(
                phoneNumber = "375"+phoneNumber.value,
                deviceModel = Build.MODEL,
                code = smsCode.value.toInt()
            )
            when (response) {
                is LoginResponse.Error -> _uiState.value =
                    LoginScreenUiState.Error(code = response.code, message = response.message)
                is LoginResponse.SMSChecked -> {
                    val token = response.refreshToken
                    tokenRepository.saveToken(token = token)
                    _uiState.value = LoginScreenUiState.Authorized
                }
                is LoginResponse.SMSSend ->
                    LoginScreenUiState.Error(code = 501, message = "code 201 is found, but expected code 202")
            }
        }
    }

    fun onRequestNewSMSClick() {
        viewModelScope.launch {
            val response = loginRepository.login(
                phoneNumber = "375"+phoneNumber.value,
                deviceModel = Build.MODEL
            )
            when (response) {
                is LoginResponse.Error -> _uiState.value =
                    LoginScreenUiState.Error(code = response.code, message = response.message)
                is LoginResponse.SMSChecked -> {}
                is LoginResponse.SMSSend -> {
                    smsCode.value = ""
                    restartTimeout()
                }
            }
        }
    }

    private fun restartTimeout() {
        viewModelScope.launch {
            timeout.value = 60
            while (timeout.value != 0) {
                delay(1000)
                timeout.value = timeout.value - 1
            }
        }
    }

    fun onErrorReturnButtonClick() {
        TODO("Not yet implemented")
    }
}