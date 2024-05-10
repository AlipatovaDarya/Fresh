package com.example.fresh.domain.models

data class Author(
    val id: Long?,
    val name: String?,
    var score: Long = 0L,
    val description: String?,
    var isExpert: Boolean = false,
)

