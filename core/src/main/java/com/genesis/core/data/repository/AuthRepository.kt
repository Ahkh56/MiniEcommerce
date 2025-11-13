package com.genesis.core.data.repository

import com.genesis.core.model.User

interface AuthRepository {

    suspend fun login(email: String, password: String): Result<User>
    suspend fun register(email: String, password: String): Result<User>
    fun logout()
    fun getCurrentUser(): User?
}