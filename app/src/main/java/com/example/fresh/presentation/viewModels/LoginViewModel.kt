package com.example.fresh.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    /*var emailLiveData : MutableLiveData<String>
    var passwordLiveData : MutableLiveData<String>*/
    var isValidEmailLiveData : MutableLiveData<Boolean>
    var isValidPasswordLiveData : MutableLiveData<Boolean>

    init {
        /*emailLiveData = MutableLiveData<String>()
        passwordLiveData = MutableLiveData<String>()*/
        isValidEmailLiveData = MutableLiveData<Boolean>(true)
        isValidPasswordLiveData = MutableLiveData<Boolean>(true)
    }

    /*fun isValidEmail(){
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailLiveData.value).matches()){
            isValidEmailLiveData.value = false
        }
    }

    fun isValidPassword(){
        if(passwordLiveData.value.toString().isEmpty()){
            isValidEmailLiveData.value = false
        }
    }*/
}