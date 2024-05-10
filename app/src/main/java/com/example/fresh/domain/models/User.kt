package com.example.fresh.domain.models

data class User(
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val role: String,
    val score: Long = 0
)

