package com.example.fresh.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fresh.firebase.repositories.AuthAppRepository
import com.google.firebase.auth.FirebaseUser

class AuthViewModel() : ViewModel() {

    var authAppRepository: AuthAppRepository
    var userLiveData : MutableLiveData<FirebaseUser?>
    var errorMessageLiveData : MutableLiveData<String>
    var authStateLiveData: MutableLiveData<AuthState>


    init {
        authAppRepository = AuthAppRepository()
        userLiveData = authAppRepository.userLiveData
        errorMessageLiveData = authAppRepository.errorMessageLiveData
        authStateLiveData = authAppRepository.authStateLiveData
    }


    /*fun getAuthStateLiveData(): MutableLiveData<AuthState> {
        return authStateLiveData
    }

    fun getUserLiveData(): MutableLiveData<FirebaseUser?>? {
        return userLiveData
    }

    fun getErrorMessageLiveData(): MutableLiveData<String> {
        return errorMessageLiveData
    }*/

    fun registerUser(email: String, password: String) {
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