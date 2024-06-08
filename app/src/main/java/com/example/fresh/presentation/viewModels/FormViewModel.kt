package com.example.fresh.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fresh.data.repositories.Repository
import com.example.fresh.domain.models.Answer
import com.example.fresh.domain.models.GeneralAnswer
import com.example.fresh.domain.models.Question
import com.example.fresh.domain.models.Vote

class FormViewModel() : ViewModel() {
    private val rep = Repository()
    val questionsLiveData = MutableLiveData<List<Question?>>()
    val isCorrectEnter = MutableLiveData(true)
    val curEvent = MutableLiveData<String?>(null)


    fun getCurrentEventID(uid: String?){
        if(uid == null){
            return
        }
        rep.getCurrentEventID(uid){id ->
            curEvent.postValue(id)
        }
    }

    fun getFormQuestions(eventID: String?){
        rep.getFormQuestions(eventID) { questions ->
            questionsLiveData.value = questions
        }
    }

    fun addAnswers(list : List<Answer?>){
        for(answer in list){
            if(answer?.eventID != null){
                rep.addAnswer(answer)
            }
        }

    }

    fun addVotes(list : List<Vote?>){
        for(vote in list){
            if(vote?.eventID != null){
                rep.addVote(vote)
            }
        }
    }


    fun isEnterCorrect(list : List<GeneralAnswer?>) : Boolean{
        for(ans in list){
            if(ans!= null){
                if(ans.hasMarks){
                    if(!сheckMarkText(ans.answerText)){
                        return false
                    }
                } else{
                    if(ans.answerText == null){
                        return false
                    }
                }
            }

        }
        return true
    }

    private fun сheckMarkText(str: String?) : Boolean{
        return str?.toIntOrNull() in 1..10
    }
}