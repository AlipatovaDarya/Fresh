package com.example.fresh.presentation.viewModels

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fresh.data.repositories.Repository
import com.example.fresh.domain.models.Author
import com.example.fresh.domain.models.Event
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.TimeZone

class EventViewModel() : ViewModel() {
    val rep = Repository()
    val finishedEvents: MutableLiveData<List<Event>> = MutableLiveData(emptyList())
    val upcomingEvents: MutableLiveData<List<Event>> = MutableLiveData(emptyList())
    val eventInfo: MutableLiveData<Event?> = MutableLiveData(null)
    val isRegisteredUser = MutableLiveData<Boolean?>(null)


    fun registerUserToEvent(uid: String?, eventID: String?){
        if(uid == null || eventID == null) return
        rep.registerUserToEvent(uid, eventID)
    }

    fun deleteUserFromEvent(uid: String?, eventID: String?){
        if(uid == null || eventID == null) return
        rep.deleteUserFromEvent(uid, eventID)
        //isRegister.value = false
    }

    /*fun isRegisteredUser(uid: String?, eventID: String?) {
        if(uid != null && eventID != null){
            rep.isRegisteredUser(uid, eventID){res ->
                isRegisteredUser.value = res
            }
        }
    }*/

    fun getFinishedEvents() {
        rep.getFinishedEvents { events ->
            finishedEvents.value = events
        }
    }

    fun getUpcomingEvents() {
        rep.getUpcomingEvents { events ->
            upcomingEvents.value = events
        }
    }

    fun getEventInfo(eventID: String?, uid: String?){
        if(uid != null && eventID != null){
            rep.getEventInfo(eventID, uid){event ->
                eventInfo.value = event
            }
        }

    }

    fun formatTimeStampToMoscowTime(timeStamp: Timestamp, context: Context): String {
        val date = timeStamp.toDate()
        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", context.resources.configuration.locales[0])
        dateFormat.timeZone = TimeZone.getTimeZone("Europe/Moscow")
        return dateFormat.format(date)
    }
}