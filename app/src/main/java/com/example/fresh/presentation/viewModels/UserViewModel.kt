package com.example.fresh.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.fresh.data.repositories.UserRepository
import com.example.fresh.domain.models.User

class UserViewModel() : ViewModel(){
    val rep = UserRepository()

    fun addUser(user: User){
        rep.addUser(user)
    }

}