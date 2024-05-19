package com.example.fresh.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fresh.data.repositories.Repository
import com.example.fresh.domain.models.Author
import com.example.fresh.domain.models.Event

class AuthorViewModel() : ViewModel(){
    private val rep = Repository()
    //val authorInfoLiveData = rep.authorInfoLiveData
    val authorInfoLiveData = MutableLiveData<Author?>()
    val authorsLiveData = MutableLiveData<List<Author?>>()
    val expertsLiveData = MutableLiveData<List<Author?>>()


    fun getAuthorInfo(id: String?){
        rep.getAuthorInfo(id){author ->
            authorInfoLiveData.value = author
        }
    }

    fun getAuthors(){
        rep.getAuthors { authors ->
            authorsLiveData.value = authors
        }

    }

    fun getExperts(){
        rep.getExperts { experts ->
            expertsLiveData.value = experts
        }
    }
}