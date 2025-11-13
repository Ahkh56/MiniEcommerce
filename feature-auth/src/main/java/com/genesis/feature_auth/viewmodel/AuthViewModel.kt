package com.genesis.feature_auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genesis.data.repository.AuthRepository
import com.genesis.domain.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _loginState = MutableStateFlow<Result<User>?>(null)
    val loginState: StateFlow<Result<User>?> = _loginState

    private val _registerState = MutableStateFlow<Result<User>?>(null)
    val registerState: StateFlow<Result<User>?> = _registerState

    private val _logoutState = MutableStateFlow<Result<Unit>?>(null)
    val logoutState: StateFlow<Result<Unit>?> = _logoutState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.login(email, password)
            _loginState.value = result
            _isLoading.value = false
        }
    }

    fun register(name: String, email: String, phone: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.register(name, email, phone, password)
            _registerState.value = result
            _isLoading.value = false
        }
    }

    fun logout() {
        viewModelScope.launch {
            val result = runCatching { repository.logout() }
            _logoutState.value = result
        }
    }
}
