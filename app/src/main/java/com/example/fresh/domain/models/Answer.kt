package com.example.fresh.domain.models

data class Answer(
    var questionID: String,
    var text: String,
    var uid: String?,
    var eventID: String?,
)

