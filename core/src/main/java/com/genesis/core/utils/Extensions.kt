package com.genesis.core.utils

import android.util.Patterns
import com.genesis.core.model.User
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Converts FirebaseUser → User model (for repository use)
 */
fun FirebaseUser.toUser(): User = User(
    id = uid,
    name = displayName ?: "",
    email = email ?: "",
    phone = phoneNumber,
    profileImageUrl = photoUrl?.toString()
)

/**
 * Returns an error message if the email is invalid, else null.
 */
fun String.validateEmail(): String? = when {
    isBlank() -> "Email cannot be empty"
    !Patterns.EMAIL_ADDRESS.matcher(this).matches() -> "Invalid email address"
    else -> null
}

/**
 * Returns an error message if the password is invalid, else null.
 */
fun String.validatePassword(): String? = when {
    isBlank() -> "Password cannot be empty"
    length < 6 -> "Password must be at least 6 characters"
    else -> null
}

/**
 * Generic safe call for suspend functions with Result wrapper.
 */
suspend inline fun <T> safeCall(crossinline action: suspend () -> T): Result<T> {
    return withContext(Dispatchers.IO) {
        try {
            Result.success(action())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
