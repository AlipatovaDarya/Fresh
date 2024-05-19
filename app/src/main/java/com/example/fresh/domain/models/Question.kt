package com.example.fresh.domain.models

data class Question(
    val id: String,
    val hasMarks: Boolean?,
    val questionText: String?
)