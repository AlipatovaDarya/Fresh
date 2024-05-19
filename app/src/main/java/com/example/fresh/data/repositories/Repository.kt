package com.example.fresh.data.repositories

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.fresh.domain.models.Author
import com.example.fresh.domain.models.Event
import com.example.fresh.domain.models.Question
import com.example.fresh.domain.models.User
import com.example.fresh.presentation.viewModels.AuthState
import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class Repository {
    val userInfoLiveData = MutableLiveData<User?>()
    //val authorInfoLiveData = MutableLiveData<Author?>()
    //val eventInfoLiveData = MutableLiveData<Event?>()

    private val db = Firebase.firestore

    fun getUserInfo(uid: String?){
        if(uid == null){
            return
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
                    userInfoLiveData.postValue(user)
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }



    fun getExperts(listener: (List<Author>) -> Unit){
        val res : MutableList<Author> = mutableListOf()
        db.collection("authors")
            .whereEqualTo("isExpert", true)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val author = Author(
                        document.id,
                        document.data["authorName"].toString(),
                        document.data["description"].toString(),
                        document.data["isExpert"].toString().toBoolean(),
                        document.data["score"].toString().toLong(),
                    )
                    res.add(author)
                }
                listener(res)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
                listener(emptyList())
            }
    }


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

    //СТАРАЯ ВЕРСИЯ
    /*fun getFinishedEvents() : List<Event>{
        val res : MutableList<Event> = mutableListOf()
        db.collection("events")
            .whereEqualTo("isExpert", true)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val event = Event(
                        document.id,
                        document.data["authorName"].toString(),
                        document.data["description"].toString(),
                        document.data["qr"].toString(),
                        document.data["status"].toString(),
                        document.data["time"].toString(),
                        )
                    res.add(event)
                    //Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
        return res
    }*/

    fun getFinishedEvents(listener: (List<Event>) -> Unit) {
        db.collection("events")
            .whereEqualTo("status", "finished")
            .get()
            .addOnSuccessListener { result ->
                val res: MutableList<Event> = mutableListOf()
                for (document in result) {
                    val id = document.id
                    val address = document.getString("address")
                    val description = document.getString("description")
                    val status = document.getString("status")
                    val time = document.getTimestamp("time")

                    val event = Event(id, address, description, status, time)
                    res.add(event)
                }
                listener(res)
            }
            .addOnFailureListener { exception ->
                Log.e("Repository", "Error getting documents: ", exception)
                listener(emptyList())
            }
    }


    fun getUpcomingEvents(listener: (List<Event>) -> Unit) {
        db.collection("events")
            .whereEqualTo("status", "upcoming")
            .get()
            .addOnSuccessListener { result ->
                val res: MutableList<Event> = mutableListOf()
                for (document in result) {
                    val id = document.id
                    val address = document.getString("address")
                    val description = document.getString("description")
                    val qr = document.getString("qr")
                    val status = document.getString("status")
                    val time = document.getTimestamp("time")

                    val event = Event(id, address, description, status, time)
                    res.add(event)
                }
                listener(res)
            }
            .addOnFailureListener { exception ->
                Log.e("Repository", "Error getting documents: ", exception)
                listener(emptyList())
            }
    }

    fun getEventInfo(id: String?, listener: (Event?) -> Unit){
        if(id == null){
            return
        }
        var event : Event? = null

        val docRef = db.collection("events").document(id)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    event = Event(
                        document.id,
                        document.getString("address"),
                        document.getString("description"),
                        document.getString("status"),
                        document.getTimestamp("time")
                    )
                    //eventInfoLiveData.postValue(event)
                    listener(event!!)
                    Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                    listener(null)
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }
    }


    fun getAuthors(listener: (List<Author>) -> Unit){
        val res : MutableList<Author> = mutableListOf()
        db.collection("authors")
            .whereEqualTo("isExpert", false)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val author = Author(
                        document.id,
                        document.data["authorName"].toString(),
                        document.data["description"].toString(),
                        document.data["isExpert"].toString().toBoolean(),
                        document.data["score"].toString().toLong(),
                    )
                    res.add(author)
                }
                listener(res)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
                listener(emptyList())
            }
    }


    fun getAuthorInfo(id: String?, listener: (Author?) -> Unit) {
        if(id == null){
            return
        }
        var author : Author? = null
        val docRef = db.collection("authors").document(id)
        docRef.get()
            .addOnSuccessListener { document ->
                //if (document != null) {
                author = Author(
                    document.id,
                    document.data?.get("authorName").toString(),
                    document.data?.get("description").toString(),
                    document.data?.get("isExpert").toString().toBoolean(),
                    document.data?.get("score").toString().toLong(),
                )
                listener(author)
                    Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data}")
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
                listener(null)
            }
    }


    /*fun getAuthorInfo(id: String?,listener: (Author?) -> Unit){
        if(id == null){
            return
        }
        var author : Author? = null
        val docRef = db.collection("authors").document(id)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    author = Author(
                        id,
                        document.data?.get("authorName")?.toString(),
                        document.data?.get("description")?.toString(),
                        document.data?.get("isExpert")?.toString().toBoolean(),
                        document.data?.get("score").toString().toLong(),
                    )
                    //authorInfoLiveData.postValue(author)
                    listener(author)
                    Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                    listener(null)
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }
    }*/

    fun updateAccFirstName(str: String, uid: String){
        val docRef = db.collection("users").document(uid)
        docRef
            .update("firstName", str)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

    fun updateAccLastName(str: String, uid: String){
        val docRef = db.collection("users").document(uid)
        docRef
            .update("lastName", str)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

    /*fun updateAccEmail(str: String, uid: String){
        val docRef = db.collection("users").document(uid)
        docRef
            .update("lastName", str)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }*/

    /*fun updateAccPassword(str: String, uid: String){
        val docRef = db.collection("users").document(uid)
        docRef
            .update("lastName", str)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }*/



    fun getFormQuestions(eventID: String?, listener: (List<Question?>) -> Unit) {
        if(eventID == null){
            return
        }
        val res : MutableList<Question?> = mutableListOf()
        var question : Question?
        val docRef = db.collection("events").document(eventID).collection("questions")
        docRef.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    question = Question(
                        document.id,
                        document.data["hasMarks"].toString().toBoolean(),
                        document.data["questionText"].toString(),
                    )
                    res.add(question)
                }
                listener(res)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
                listener(emptyList())
            }
    }
}


