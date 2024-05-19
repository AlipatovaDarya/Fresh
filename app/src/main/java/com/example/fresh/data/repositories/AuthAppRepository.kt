package com.example.fresh.data.repositories

import androidx.lifecycle.MutableLiveData
import com.example.fresh.domain.models.User
import com.example.fresh.presentation.viewModels.AuthState
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class AuthAppRepository() {
    val rep = Repository()
    var firebaseAuth : FirebaseAuth = Firebase.auth
    var firebaseUserLiveData = MutableLiveData<FirebaseUser?>()
    var userInfoLiveData = MutableLiveData<User?>()
    var errorMessageLiveData : MutableLiveData<String> = MutableLiveData()
    var authStateLiveData: MutableLiveData<AuthState> = MutableLiveData()
    var accDeleted: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        firebaseAuth.addAuthStateListener { auth: FirebaseAuth ->
            val user = auth.currentUser
            if (user != null) {
                firebaseUserLiveData.postValue(user)
                authStateLiveData.postValue(AuthState.AUTHENTICATED)
            } else {
                firebaseUserLiveData.postValue(null)
                authStateLiveData.postValue(AuthState.UNAUTHENTICATED)
            }
        }
    }

    fun registerUser(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = FirebaseAuth.getInstance().currentUser
                    firebaseUserLiveData.postValue(currentUser)
                    firebaseUserLiveData.value?.sendEmailVerification()
                    authStateLiveData.postValue(AuthState.AUTHENTICATED)
                } else {
                    errorMessageLiveData.postValue(task.exception!!.message)
                }
            }
    }


    fun loginUser(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    firebaseUserLiveData.postValue(user)
                    rep.getUserInfo(firebaseUserLiveData.value?.uid)
                    //val role = AuthState.valueOf(userInfoLiveData.value?.role.toString())
                    authStateLiveData.postValue(AuthState.AUTHENTICATED)
                } else {
                    errorMessageLiveData.postValue(task.exception!!.message)
                }
            }
    }

    fun logoutUser() {
        firebaseAuth.signOut()
    }

    fun deleteAccount() {
        val user = firebaseAuth.currentUser
        user?.delete()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                firebaseUserLiveData.postValue(null)
                authStateLiveData.postValue(AuthState.UNAUTHENTICATED)
                accDeleted.value = true
            } else {
                errorMessageLiveData.postValue(task.exception!!.message)
            }
        }
    }

    fun changePassword(currentPassword: String?, newPassword: String?) {
        val user = firebaseAuth.currentUser
        if (user != null) {
            val credential = EmailAuthProvider.getCredential(
                user.email!!,
                currentPassword!!
            )
            user.reauthenticate(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        user.updatePassword(newPassword!!)
                            .addOnCompleteListener { passwordUpdateTask ->
                                if (passwordUpdateTask.isSuccessful) {
                                } else {
                                    errorMessageLiveData.postValue(passwordUpdateTask.exception!!.message)
                                }
                            }
                    } else {
                        errorMessageLiveData.postValue(task.exception!!.message)
                    }
                }
        }
    }
}