package com.genesis.feature_profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genesis.data.repository.AuthRepository
import com.genesis.data.repository.UserRepository
import com.genesis.domain.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    private val _updateResult = MutableStateFlow<Result<Unit>?>(null)
    val updateResult: StateFlow<Result<Unit>?> = _updateResult.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            _isLoading.value = true
            authRepository.getCurrentUser()?.id?.let { userId ->
                val result = userRepository.getUser(userId)
                result.onSuccess { _user.value = it }
            }
            _isLoading.value = false
        }
    }

    fun updateUserProfile(name: String, phone: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val currentUser = _user.value ?: return@launch

            val updatedUser = currentUser.copy(
                name = name,
                phone = phone
            )
            val updateResult = userRepository.updateUser(updatedUser)
            _updateResult.value = updateResult
            if (updateResult.isSuccess) {
                _user.value = updatedUser
            }

            _isLoading.value = false
        }
    }
}
