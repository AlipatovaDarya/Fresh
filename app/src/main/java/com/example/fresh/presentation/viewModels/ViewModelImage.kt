package com.example.fresh.presentation.viewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fresh.data.repositories.Repository
import com.example.fresh.data.repositories.StorageRepository

class ViewModelImage() : ViewModel() {
    private val storRep = StorageRepository()
    val imageUri = MutableLiveData<String?>()

    fun getImageUri(path: String) {
        try {
            storRep.getImageUri(path) { uri ->
                imageUri.postValue(uri)
            }
        } catch (e: Exception){
            Log.e(TAG, e.message.toString())
        }

    }
}