package com.example.fresh.presentation.viewModels

import android.media.Image
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fresh.data.repositories.Repository
/*import com.example.fresh.data.repositories.StorageRepository
import com.example.fresh.data.repositories.StorageRepositoryImpl*/
import com.example.fresh.domain.models.Author
import com.example.fresh.domain.models.Event
import kotlinx.coroutines.launch
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.ImageBitmapConfig
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.fresh.data.repositories.StorageRepository
import com.example.fresh.domain.models.AudioLink


class AuthorViewModel() : ViewModel() {
    private val rep = Repository()
    //private val storRep = StorageRepository()
    val authorInfoLiveData = MutableLiveData<Author?>()
    val authorsLiveData = MutableLiveData<List<Author?>>()
    val expertsLiveData = MutableLiveData<List<Author?>>()
    val audioProfile = MutableLiveData<List<AudioLink?>>()
    /*val imageUri = MutableLiveData<String?>()

    fun getImageUri(path: String) {
        storRep.getImageUri(path) { uri ->
            imageUri.postValue(uri)
        }
    }*/


    fun getAuthorInfo(id: String?) {
        rep.getAuthorInfo(id) { author ->
            authorInfoLiveData.postValue(author)
        }
    }

    fun getAuthorAudioProfiles(id: String?) {
        rep.getAuthorAudioProfiles(id) { audioProfiles ->
            if(audioProfiles != null){
                audioProfile.value = audioProfiles
            }
        }
    }

    fun getAuthors() {
        rep.getAuthors { authors ->
            authorsLiveData.value = authors
        }
    }


    fun getSortedAuthors() {
        rep.getAuthors { authors ->
            authorsLiveData.value = authors.sortedByDescending { author: Author -> author.score }
        }
    }

    fun getExperts() {
        rep.getExperts { experts ->
            expertsLiveData.value = experts
        }
    }

}