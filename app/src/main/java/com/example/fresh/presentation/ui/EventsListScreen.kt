package com.example.fresh.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fresh.R
import com.example.fresh.domain.models.Event
import com.example.fresh.presentation.ui.common.TopBar
import com.example.fresh.presentation.viewModels.AuthViewModel
import com.example.fresh.presentation.viewModels.EventViewModel

@Composable
fun EventsListScreen(
    navController: NavHostController,
    viewModelState: AuthViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.beige))
    ) {
        TopBar("Мероприятия", true, navController)

        val selectedButton = remember { mutableStateOf(true) }

        val viewModel = EventViewModel()
        viewModel.getFinishedEvents()
        viewModel.getUpcomingEvents()

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 30.dp),
        ) {
            Button(
                onClick = {
                    selectedButton.value = true
                },
                modifier = Modifier
                    .padding(end = 36.dp),
                colors = if (selectedButton.value) ButtonDefaults.buttonColors(
                    containerColor = colorResource(
                        id = R.color.lilac
                    )
                )
                else ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.white))
            ) {
                Text(
                    text = "Прошедшие",
                    style = if (selectedButton.value) TextStyle(
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.white)
                    )
                    else TextStyle(fontSize = 14.sp, color = colorResource(id = R.color.lilac))
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Button(
                onClick = {
                    selectedButton.value = false
                },
                colors = if (!selectedButton.value) ButtonDefaults.buttonColors(
                    containerColor = colorResource(
                        id = R.color.lilac
                    )
                )
                else ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.white))
            ) {
                Text(
                    text = "Предстоящие",
                    style = if (selectedButton.value) TextStyle(
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.lilac)
                    )
                    else TextStyle(fontSize = 14.sp, color = colorResource(id = R.color.white))
                )
            }

        }

        var showDialogRegisration = remember { mutableStateOf(false) }

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            val events: State<List<Event>>
            if (selectedButton.value) {
                events = viewModel.finishedEvents.observeAsState(emptyList())
            } else {
                events = viewModel.upcomingEvents.observeAsState(emptyList())
            }
            //начало дин. списка
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                items(events.value) { event ->
                    val isRegistered = remember { mutableStateOf(false) }
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.orange)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 12.dp)
                            .clickable {
                                navController.navigate("eventScreen")
                                viewModelState.curPageIDLiveData.value = event.id
                            },
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            val time = viewModel.formatTimeStampToMoscowTime(event.time!!, LocalContext.current)
                            Text(
                                text = "Фреш\n${time} Мск",
                                style = TextStyle(
                                    fontSize = 18.sp, color = colorResource(id = R.color.white)
                                )
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            if (!selectedButton.value) {
                                Button(
                                    onClick = {
                                        showDialogRegisration.value = true
                                    },
                                    colors = if (isRegistered.value) ButtonDefaults.buttonColors(
                                        containerColor = colorResource(id = R.color.white)
                                    ) else ButtonDefaults.buttonColors(
                                        containerColor = colorResource(
                                            id = R.color.green
                                        )
                                    ),
                                    modifier = Modifier.padding(start = 50.dp)

                                ) {
                                    Text(
                                        text = if (!isRegistered.value) "регистрация" else "вы зарегистрированы",
                                        style = if (isRegistered.value) TextStyle(
                                            fontSize = 13.sp,
                                            color = colorResource(id = R.color.green)
                                        ) else TextStyle(
                                            fontSize = 12.sp,
                                            color = colorResource(id = R.color.white)
                                        )
                                    )
                                }
                                if (showDialogRegisration.value) {
                                    AlertDialog(
                                        onDismissRequest = {
                                            showDialogRegisration.value = false
                                        },
                                        title = { Text("Регистрация") },
                                        text = { Text("Вы действительно хотите зарегистрироваться на мероприятие?") },
                                        confirmButton = {
                                            Button(
                                                onClick = {
                                                    showDialogRegisration.value = false
                                                    isRegistered.value = true
                                                },
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = colorResource(id = R.color.orange)
                                                ),
                                            ) {
                                                Text("Зарегистрироваться")
                                            }
                                        },
                                        dismissButton = {
                                            Button(
                                                onClick = {
                                                    showDialogRegisration.value = false
                                                },
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = colorResource(id = R.color.lilac)
                                                ),
                                            ) {
                                                Text("Отмена")
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

            }


            /*LazyColumn(

            ) {
                itemsIndexed(data.value) {index, item ->

                }
            }
            //конец дин. списка*/
        }
    }
}