package com.example.fresh.domain.models

data class Vote(
    var uid: String?,
    var score: Int,
    var eventID: String?,
    var authorID: String?
)