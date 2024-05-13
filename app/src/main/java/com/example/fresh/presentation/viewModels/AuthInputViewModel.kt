package com.example.fresh.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fresh.data.repositories.AuthAppRepository

class AuthInputViewModel() : ViewModel() {
    var isCorrectName: MutableLiveData<Boolean> = MutableLiveData(true)
    var isCorrectLastName: MutableLiveData<Boolean> = MutableLiveData(true)
    var isCorrectEmail: MutableLiveData<Boolean> = MutableLiveData(true)
    var isCorrectPassword1: MutableLiveData<Boolean> = MutableLiveData(true)
    var isCorrectPassword2: MutableLiveData<Boolean> = MutableLiveData(true)

    fun checkName(s: String?){
        if(s == null) {
            isCorrectName.setValue(false)
            return
        }
        isCorrectName.setValue(s.length > 1)
    }

    fun checkLastName(s: String?){
        if(s == null) {
            isCorrectLastName.setValue(false)
            return
        }
        isCorrectLastName.setValue(s.length > 1)
    }

    fun checkPassword1(s: String?){
        if(s == null) {
            isCorrectPassword1.setValue(false)
            return
        }
        isCorrectPassword1.setValue(s.length >= 6)
    }

    fun checkPassword2(s1: String?, s2: String?){
        if(s1 == null || s2 == null) {
            isCorrectPassword2.setValue(false)
            return
        }
        isCorrectPassword2.setValue(s1 == s2)
    }

    fun checkEmail(s: String?){
        if(s == null) {
            isCorrectEmail.setValue(false)
            return
        }
        //val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
        isCorrectEmail.setValue(android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches())
    }


    fun isRegisterCorrect() :Boolean{
        return isCorrectName.value!! && isCorrectLastName.value!! && isCorrectEmail.value!! && isCorrectPassword1.value!! && isCorrectPassword2.value!!
    }

    fun isEnterCorrect() :Boolean{
        return isCorrectEmail.value!! && isCorrectPassword1.value!!
    }



}