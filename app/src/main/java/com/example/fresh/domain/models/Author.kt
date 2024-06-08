package com.example.fresh.domain.models

data class Author(
    val id: String,
    val name: String?,
    val description: String?,
    var isExpert: Boolean = false,
    var score : Int = 0,
    var audioLinks : MutableList<AudioLink> = mutableListOf()
)

