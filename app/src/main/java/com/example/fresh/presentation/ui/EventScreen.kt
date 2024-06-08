package com.example.fresh.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.fresh.R
import com.example.fresh.domain.models.Event
import com.example.fresh.presentation.ui.common.TopBar
import com.example.fresh.presentation.viewModels.AuthViewModel
import com.example.fresh.presentation.viewModels.EventViewModel
import com.example.fresh.presentation.viewModels.ViewModelImage

@Composable
fun EventScreen(
    navController: NavHostController,
    viewModelState: AuthViewModel,
) {
    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.beige))
            .fillMaxSize()
    ) {
        TopBar(
            currentScreen = "Страница мероприятия",
            arrowBack = true,
            navController = navController
        )
        val viewModel = EventViewModel()
        viewModelState.curItemIDLiveData.value?.let { viewModel.getEventInfo(it, viewModelState.userLiveData.value?.uid) }
        val event = viewModel.eventInfo.observeAsState()
        if(event.value != null){
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                /*val viewModelImage = ViewModelImage()
                viewModelImage.getImageUri("events/${viewModelState.curItemIDLiveData.value}.png")
                val imageUri = viewModelImage.imageUri.observeAsState(initial = "")
                if (imageUri.value?.isNotEmpty() == true) {
                    AsyncImage(
                        model = imageUri.value,
                        contentDescription = "event image",
                        modifier = Modifier
                            .padding(20.dp)
                    )
                }*/

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Фреш ${viewModel.formatTimeStampToMoscowTime(event.value?.time!!, LocalContext.current)}",
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = colorResource(id = R.color.dark_gray)
                    ),
                    modifier = Modifier.padding(bottom = 18.dp, start = 5.dp),
                )
                Text(
                    modifier = Modifier.padding(bottom = 18.dp),
                    text = event.value?.description ?: "",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.dark_gray)
                    ),
                )
                /*Text(
                    text = "Победители",
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = colorResource(id = R.color.dark_gray)
                    ),
                    modifier = Modifier.padding(bottom = 18.dp, start = 5.dp),
                )*/
            }
        }

    }
}