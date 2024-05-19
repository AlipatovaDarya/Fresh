package com.example.fresh.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fresh.data.repositories.AuthAppRepository
import com.google.firebase.auth.FirebaseUser

class AuthViewModel() : ViewModel() {

    var authAppRepository: AuthAppRepository = AuthAppRepository()
    var userLiveData : MutableLiveData<FirebaseUser?> = authAppRepository.firebaseUserLiveData
    var errorMessageLiveData : MutableLiveData<String> = authAppRepository.errorMessageLiveData
    var authStateLiveData: MutableLiveData<AuthState> = authAppRepository.authStateLiveData
    var curPageIDLiveData = MutableLiveData("")
    var accDeleted = authAppRepository.accDeleted
    //var loggedOut  = MutableLiveData(false)


    fun registerUser(email: String, password: String){
        authAppRepository.registerUser(email, password)
    }

    fun loginUser(email: String?, password: String?) {
        authAppRepository.loginUser(email!!, password!!)
    }

    fun logoutUser() {
        authAppRepository.logoutUser()
    }

    fun deleteAccount() {
        authAppRepository.deleteAccount()
    }

    fun changePassword(currentPassword: String?, newPassword: String?) {
        authAppRepository.changePassword(currentPassword, newPassword)
    }


}