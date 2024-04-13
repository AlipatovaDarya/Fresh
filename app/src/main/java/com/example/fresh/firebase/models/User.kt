package com.example.fresh.firebase.models

data class User(
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val role: String,
    val grade: Long = 0
)