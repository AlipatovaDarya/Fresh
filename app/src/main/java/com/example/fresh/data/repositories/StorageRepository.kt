package com.example.fresh.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fresh.domain.models.Author
import com.google.firebase.storage.FirebaseStorage


class StorageRepository {
    private val storageRef = FirebaseStorage.getInstance()

    fun getImageUri(path: String, listener: (String?) -> Unit) {
        // storageRef.getReference("authors/Avanti.png").downloadUrl.addOnSuccessListener { uri ->
        storageRef.getReference(path).downloadUrl.addOnSuccessListener { uri ->
            listener(uri.toString())
        }.addOnFailureListener {
            listener(null)
        }
    }
}

