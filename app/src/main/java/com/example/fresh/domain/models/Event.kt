package com.example.fresh.domain.models

import java.util.Date

data class Event(
    val id: Long,
    val time : Date?,
    val address: String?,
    val description: String?,
)
