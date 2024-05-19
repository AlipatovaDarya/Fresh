package com.example.fresh.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fresh.data.repositories.Repository
import com.example.fresh.domain.models.User

class UserViewModel() : ViewModel(){
    val rep = Repository()
    val userInfoLiveData = rep.userInfoLiveData

    fun getUserInfo(uid : String?){
        rep.getUserInfo(uid)
    }
    fun addUser(user: User){
        rep.addUser(user)
    }

    fun updateAccFirstName(str: String, uid: String){
        rep.updateAccFirstName(str, uid)
    }

    fun updateAccLastName(str: String, uid: String){
        rep.updateAccLastName(str, uid)
    }



}