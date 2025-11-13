package com.genesis.data.repository

import com.genesis.data.remote.FirebaseAuthDataSource
import com.genesis.domain.model.User

class AuthRepositoryImpl(
    private val firebaseAuthDataSource: FirebaseAuthDataSource,
    private val userRepository: UserRepository
): AuthRepository {

    override suspend fun login(email: String, password: String): Result<User> {
        return firebaseAuthDataSource.login(email, password).map { firebaseUser ->
            User(id = firebaseUser.uid, email = firebaseUser.email ?: "")
        }
    }

    override suspend fun register(
        name: String,
        email: String,
        phone: String,
        password: String
    ): Result<User> {
        return firebaseAuthDataSource.register(email, password).map { firebaseUser ->
            val user = User(
                id = firebaseUser.uid,
                name = name,
                email = firebaseUser.email ?: "",
                phone = phone
            )
            userRepository.createUser(user)
            logout() // Immediately log out the user
            user
        }
    }

    override fun logout() = firebaseAuthDataSource.logout()

    override fun getCurrentUser(): User? {
        val firebaseUser = firebaseAuthDataSource.getCurrentUser() ?: return null
        return User(id = firebaseUser.uid, email = firebaseUser.email ?: "")
    }
}
