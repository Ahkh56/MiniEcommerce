package com.genesis.core.data.remote

import com.genesis.core.utils.safeCall
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class FirebaseAuthDataSource(private val firebaseAuth: FirebaseAuth) {

    suspend fun login(email: String, password: String): Result<FirebaseUser> {
        return safeCall {
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            authResult.user!!
        }
    }

    suspend fun register(email: String, password: String): Result<FirebaseUser> {
        return safeCall {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            authResult.user!!
        }
    }

    fun logout() {
        firebaseAuth.signOut()
    }

    fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser
}