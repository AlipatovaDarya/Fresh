package com.example.fresh.data.repositories

import androidx.lifecycle.MutableLiveData
import com.example.fresh.presentation.viewModels.AuthState
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class AuthAppRepository() {

    var firebaseAuth : FirebaseAuth = Firebase.auth

    var userLiveData : MutableLiveData<FirebaseUser?>

    var errorMessageLiveData : MutableLiveData<String>

    var authStateLiveData: MutableLiveData<AuthState>

    val rep = UserRepository()


    init {
        userLiveData = MutableLiveData<FirebaseUser?>()

        errorMessageLiveData = MutableLiveData()

        authStateLiveData = MutableLiveData()

        firebaseAuth.addAuthStateListener { auth: FirebaseAuth ->
            val user = auth.currentUser
            if (user != null) {
                userLiveData.postValue(user)
                authStateLiveData.postValue(AuthState.AUTHENTICATED)
            } else {
                userLiveData.postValue(null)
                authStateLiveData.postValue(AuthState.UNAUTHENTICATED)
            }
        }
    }

    fun registerUser(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = FirebaseAuth.getInstance().currentUser
                    userLiveData.postValue(currentUser)
                    userLiveData.value?.sendEmailVerification()
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
                    userLiveData.postValue(user)
                    val userInfo = rep.getUser(userLiveData.value?.uid)
                    val role = AuthState.valueOf(userInfo?.role.toString())
                    authStateLiveData.postValue(role)
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
                userLiveData.postValue(null)
                authStateLiveData.postValue(AuthState.UNAUTHENTICATED)
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