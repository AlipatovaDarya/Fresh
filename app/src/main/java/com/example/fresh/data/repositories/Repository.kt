package com.example.fresh.data.repositories

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.fresh.domain.models.Answer
import com.example.fresh.domain.models.AudioLink
import com.example.fresh.domain.models.Author
import com.example.fresh.domain.models.Event
import com.example.fresh.domain.models.Question
import com.example.fresh.domain.models.User
import com.example.fresh.domain.models.Vote
import com.example.fresh.presentation.viewModels.AuthState
import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class Repository {
    val userInfoLiveData = MutableLiveData<User?>()
    private val db = Firebase.firestore

    fun getUserInfo(uid: String?) {
        if (uid == null) {
            return
        }
        var user: User? = null
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


    fun getExperts(listener: (List<Author>) -> Unit) {
        val res: MutableList<Author> = mutableListOf()
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
                        document.data["score"].toString().toInt(),
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


    fun addUser(user: User) {
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

    fun getEventInfo(id: String?, uid: String, listener: (Event?) -> Unit) {
        if (id == null) {
            return
        }
        var event: Event? = null

        val docRef = db.collection("events").document(id)
        docRef.get()
            .addOnSuccessListener { document ->
                val eventGuests = (document.data?.get("eventGuests") as? MutableList<String>)
                var flag = false
                if (eventGuests != null) {
                    for (guest in eventGuests) {
                        if (guest == uid) {
                            flag = true
                        }
                    }
                }
                if (document != null) {
                    event = Event(
                        document.id,
                        document.getString("address"),
                        document.getString("description"),
                        document.getString("status"),
                        document.getTimestamp("time"),
                        MutableLiveData(flag)
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


    fun getAuthors(listener: (List<Author>) -> Unit) {
        val res: MutableList<Author> = mutableListOf()
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
                        document.data["score"].toString().toInt(),
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


    fun getAuthorAudioProfiles(id: String?, listener: (MutableList<AudioLink>?) -> Unit){
        if (id == null) {
            return
        }
        val audioLinks = mutableListOf<AudioLink>()
        db.collection("authors").document(id).collection("audioProfiles").get()
            .addOnSuccessListener() { result ->
                for (doc in result) {
                    Log.e(TAG, "DocumentSnapshot data: ${doc.data}")
                    audioLinks.add(
                        AudioLink(
                            doc.id,
                            doc.getString("platformName") ?: "",
                            doc.getString("linkText") ?: ""
                        )
                    )
                }
                listener(audioLinks)
            }
    }


    fun getAuthorInfo(id: String?, listener: (Author?) -> Unit) {
        if (id == null) {
            return
        }
        db.collection("authors").document(id)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val author = Author(
                        document.id,
                        document.getString("authorName") ?: "",
                        document.getString("description") ?: "",
                        document.getBoolean("isExpert") ?: false,
                        document.getLong("score")?.toInt() ?: 0,
                    )
                    listener(author)
                    Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                    listener(null)
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
                listener(null)
            }
    }

    fun updateAccFirstName(str: String, uid: String) {
        val docRef = db.collection("users").document(uid)
        docRef
            .update("firstName", str)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

    fun updateAccLastName(str: String, uid: String) {
        val docRef = db.collection("users").document(uid)
        docRef
            .update("lastName", str)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }


    fun isRegisteredUser(uid: String, eventID: String?, listener: (Boolean) -> Unit) {
        db.collection("users").document(uid)
            .get()
            .addOnSuccessListener() { document ->
                var eventsArray: MutableList<String>? = mutableListOf()
                try {
                    eventsArray = (document.data?.get("eventsArray") as? MutableList<String>)
                } catch (e: Exception) {
                    Log.e(TAG, e.message.toString())
                }
                if (eventsArray != null && eventsArray.size > 0) {
                    var flag = false
                    for (event in eventsArray) {
                        if (event == eventID) {
                            Log.e(TAG, "event reg = ${eventID}")
                            flag = true
                            listener(true)
                        }
                    }
                    if (!flag) {
                        listener(false)
                    }
                } else {
                    listener(false)
                }
            }.addOnFailureListener() {
                listener(false)
            }
    }


    fun registerUserToEvent(uid: String, eventID: String) {
        db.collection("events").document(eventID).get()
            .addOnSuccessListener() { document ->
                if (document.exists()) {
                    var eventGuests: MutableList<String>? = mutableListOf()
                    try {
                        eventGuests = (document.data?.get("eventGuests") as? MutableList<String>)
                    } catch (e: Exception) {
                        Log.e(TAG, e.message.toString())
                    }
                    if ((eventGuests?.size ?: 0) > 0) {
                        eventGuests?.add(uid)
                    } else {
                        eventGuests = mutableListOf(uid)
                    }

                    db.collection("events").document(eventID)
                        .update("eventGuests", eventGuests)
                        .addOnSuccessListener {
                            Log.d(
                                TAG,
                                "DocumentSnapshot successfully updated!"
                            )
                        }
                        .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }.addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }



        db.collection("users").document(uid).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    var eventsArr: MutableList<String>? = mutableListOf()
                    try {
                        eventsArr = (document.data?.get("eventsArray") as? MutableList<String>)
                    } catch (e: Exception) {
                        Log.e(TAG, e.message.toString())
                    }
                    if ((eventsArr?.size ?: 0) > 0) {
                        eventsArr?.add(eventID)
                    } else {
                        eventsArr = mutableListOf(eventID)
                    }
                    db.collection("users").document(uid)
                        .update("eventsArray", eventsArr)
                        .addOnSuccessListener {
                            Log.d(
                                TAG,
                                "DocumentSnapshot successfully updated!"
                            )
                        }
                        .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }.addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }
    }


    fun deleteUserFromEvent(uid: String, eventID: String) {
        db.collection("events").document(eventID).get()
            .addOnSuccessListener() { document ->
                if (document.exists()) {
                    var eventGuests: MutableList<String>? = mutableListOf()
                    try {
                        eventGuests = (document.data?.get("eventGuests") as? MutableList<String>)
                    } catch (e: Exception) {
                        Log.e(TAG, e.message.toString())
                    }
                    if ((eventGuests?.size ?: 0) > 0) {
                        eventGuests?.remove(uid)
                    } else {
                        eventGuests = mutableListOf()
                    }

                    db.collection("events").document(eventID)
                        .update("eventGuests", eventGuests)
                        .addOnSuccessListener {
                            Log.d(
                                TAG,
                                "DocumentSnapshot successfully updated!"
                            )
                        }
                        .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }.addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }



        db.collection("users").document(uid).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    var eventsArr: MutableList<String>? = mutableListOf()
                    try {
                        eventsArr = (document.data?.get("eventsArray") as? MutableList<String>)
                    } catch (e: Exception) {
                        Log.e(TAG, e.message.toString())
                    }
                    if ((eventsArr?.size ?: 0) > 0) {
                        eventsArr?.remove(eventID)
                    } else {
                        eventsArr = mutableListOf()
                    }
                    db.collection("users").document(uid)
                        .update("eventsArray", eventsArr)
                        .addOnSuccessListener {
                            Log.d(
                                TAG,
                                "DocumentSnapshot successfully updated!"
                            )
                        }
                        .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }.addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }
    }


    fun getCurrentEventID(uid: String, listener: (String?) -> Unit) {
        var flag = false
        db.collection("events")
            .whereEqualTo("status", "underway")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val id = document.id
                    val eventGuestsRef = document.reference.collection("eventGuests")
                    eventGuestsRef.get().addOnSuccessListener { eventGuest ->
                        for (guest in eventGuest.documents) {
                            if (uid == guest.data?.get("userID")?.toString()) {
                                flag = true
                                listener(id)
                            }
                        }
                    }
                }
                if (!flag) {
                    listener(null)
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Repository", "Error getting documents: ", exception)
                listener(null)
            }
    }


    fun getFormQuestions(eventID: String?, listener: (List<Question?>) -> Unit) {
        if (eventID == null) {
            return
        }
        val res: MutableList<Question?> = mutableListOf()
        var question: Question?
        val docRef = db.collection("events").document(eventID).collection("questions")
        docRef.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    question = Question(
                        document.id,
                        document.data["authorName"].toString(),
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


    fun addAnswer(
        answer: Answer
    ) {
        val newAnswer = hashMapOf(
            "answerText" to answer.text,
            "userID" to answer.uid
        )
        db.collection("events").document(answer.eventID!!)
            .collection("questions").document(answer.questionID)
            .collection("answers").document()
            .set(newAnswer)
            .addOnSuccessListener {}
            .addOnFailureListener {}
    }


    fun addVote(
        vote: Vote
    ) {
        val newVote = hashMapOf(
            "authorID" to vote.authorID,
            "mark" to vote.score,
            "userID" to vote.uid
        )
        db.collection("events").document(vote.eventID!!)
            .collection("votes").document()
            .set(newVote)
            .addOnSuccessListener {}
            .addOnFailureListener {}
        db.collection("authors").document(vote.authorID!!).get()


        db.collection("authors").document(vote.authorID!!)
            .get()
            .addOnSuccessListener() { document ->
                val score = document.data?.get("score").toString().toInt()
                db.collection("authors").document(vote.authorID!!)
                    .update("score", score + vote.score)
            }
    }


}


