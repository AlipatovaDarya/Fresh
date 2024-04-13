package com.example.fresh.firebase.models

data class Author(
    val id: Long?,
    val name: String?,
    var score: Long = 0L,
    val description: String?,
    var isExpert: Boolean = false,
)