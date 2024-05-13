package com.example.fresh.domain.models

import com.example.fresh.presentation.viewModels.AuthState

data class User(
    val uid: String,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val role: AuthState?,
    val score: Long = 0L
)

