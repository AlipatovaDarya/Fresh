package com.example.fresh.data.repositories

import android.content.ContentValues.TAG
import android.util.Log
import com.example.fresh.domain.models.User
import com.example.fresh.presentation.viewModels.AuthState
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class UserRepository {
    val db = Firebase.firestore

    fun addUser(user : User){
        val newUser = hashMapOf(
            "email" to user.email,
            "firstName" to user.firstName,
            "lastName" to user.lastName,
            "role" to user.role.toString(),
            "score" to user.score
        )
        db.collection("users").document(user.uid)
            .set(newUser)
            .addOnSuccessListener {}
            .addOnFailureListener {}
    }

    fun getUser(uid: String?) : User?{
        if(uid == null){
            return null
        }
        var user : User? = null

        val docRef = db.collection("users").document(uid)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    user = User(
                        uid,
                        document.data?.get("firstName")?.toString(),
                        document.data?.get("lastName")?.toString(),
                        document.data?.get("email")?.toString(),
                        AuthState.valueOf(document.data?.get("role")?.toString()!!),
                        document.data?.get("score").toString().toLong(),
                    )
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
        return user
    }
}


