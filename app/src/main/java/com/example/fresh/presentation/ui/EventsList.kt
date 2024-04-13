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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fresh.R
import com.example.fresh.presentation.ui.common.TopBar

@Composable
fun EventsListScreen(
    navController: NavHostController,
    lastEvents: List<String>,
    upcomingEvents: List<String>
) {
    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.beige))
    ) {
        TopBar("Рейтинги", true, true, navController)

        var selectedButton = remember { mutableStateOf(1) }
        val data = remember { mutableStateOf(lastEvents) }

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 46.dp),
        ) {
            Button(
                onClick = {
                    selectedButton.value = 1
                    data.value = lastEvents
                },
                modifier = Modifier
                    .size(width = 176.dp, height = 50.dp)
                    .padding(end = 36.dp),
                colors = if (selectedButton.value == 1) ButtonDefaults.buttonColors(
                    containerColor = colorResource(
                        id = R.color.lilac
                    )
                )
                else ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.white))
            ) {
                Text(
                    text = "Прошедшие мероприятия",
                    style = if (selectedButton.value == 1) TextStyle(
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.white)
                    )
                    else TextStyle(fontSize = 14.sp, color = colorResource(id = R.color.lilac))
                )
            }

            Button(
                onClick = {
                    selectedButton.value = 2
                    data.value = upcomingEvents
                },
                modifier = Modifier.size(width = 170.dp, height = 50.dp),
                colors = if (selectedButton.value == 2) ButtonDefaults.buttonColors(
                    containerColor = colorResource(
                        id = R.color.lilac
                    )
                )
                else ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.white))
            ) {
                Text(
                    text = "Предстоящие мероприятия",
                    style = if (selectedButton.value == 1) TextStyle(
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
            //начало дин. списка
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                itemsIndexed(data.value) {index, item ->
                    var isRegistered = remember { mutableStateOf(false) }
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.orange)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 10.dp)
                            .clickable {
                                navController.navigate("eventScreen")
                            },
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Row {
                                Text(
                                    text = "Фреш 10.10.10",
                                    style = TextStyle(
                                        fontSize = 18.sp, color = colorResource(id = R.color.white)
                                    )
                                )
                                if (selectedButton.value == 2) {
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
                                            text = if (!isRegistered.value) "зарегистрироваться" else "вы зарегистрированы",
                                            style = if (isRegistered.value) TextStyle(
                                                fontSize = 10.sp,
                                                color = colorResource(id = R.color.green)
                                            ) else TextStyle(
                                                fontSize = 10.sp,
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

                            Text(
                                text = item,
                                style = TextStyle(
                                    fontSize = 12.sp, color = colorResource(id = R.color.white)
                                )
                            )
                        }
                    }
                }
            }
            //конец дин. списка
        }
    }
}