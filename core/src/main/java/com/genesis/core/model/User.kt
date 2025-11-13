package com.genesis.core.model

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String? = null,
    val profileImageUrl: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)