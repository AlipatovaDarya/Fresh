package com.example.fresh.domain.models

import androidx.lifecycle.MutableLiveData

data class Event(
    val id: String,
    val address: String?,
    val description: String?,
    val status: String?,
    val time: com.google.firebase.Timestamp?,
    val curUserIsRegister: MutableLiveData<Boolean?> = MutableLiveData(false)
)

