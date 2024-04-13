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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fresh.R
import com.example.fresh.presentation.ui.common.TopBar

@Composable
fun RankingScreen(navController: NavHostController, sortedAuthors: List<String>, sortedVisitors: List<String>) {
    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.beige))
    ) {
        TopBar("Рейтинги", true, true, navController)

        var selectedButton = remember { mutableStateOf(1) }
        val data = remember { mutableStateOf(sortedAuthors) }

        Row(Modifier.padding(16.dp)) {
            Button(
                onClick = {
                    selectedButton.value = 1
                    data.value = sortedAuthors
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                colors = if (selectedButton.value == 1) ButtonDefaults.buttonColors(containerColor  = colorResource(id = R.color.lilac))
                else ButtonDefaults.buttonColors(containerColor  = colorResource(id = R.color.white))
            ) {
                Text(
                    text = "Рейтинг авторов",
                    style = if (selectedButton.value == 1) TextStyle(fontSize = 14.sp, color = colorResource(id = R.color.white))
                    else TextStyle(fontSize = 14.sp, color = colorResource(id = R.color.lilac))
                )
            }

            Button(
                onClick = {
                    selectedButton.value = 2
                    data.value = sortedVisitors
                },
                modifier = Modifier.weight(1f),
                colors = if (selectedButton.value == 2) ButtonDefaults.buttonColors(containerColor  = colorResource(id = R.color.lilac))
                else ButtonDefaults.buttonColors(containerColor  = colorResource(id = R.color.white))
            ) {
                Text(
                    text = "Рейтинг зрителей",
                    style = if (selectedButton.value == 1) TextStyle(fontSize = 14.sp, color = colorResource(id = R.color.lilac))
                    else TextStyle(fontSize = 14.sp, color = colorResource(id = R.color.white))
                    )

                //if (selectedButton.value == 1)
            }

        }



        Column(verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            //начало дин. списка авторов
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                var i: Long = 1
                items(data.value) { item ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.orange)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 10.dp)
                            .clickable {
                                navController.navigate("authorScreen")
                            },
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = i.toString(),
                                modifier = Modifier.padding(start = 10.dp, end = 2.dp),
                                color = colorResource(
                                    id = R.color.white
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = item,
                                style = TextStyle(fontSize = 14.sp, color = colorResource(id = R.color.white)
                                )
                            )
                        }
                        i++
                    }

                }
            }
            //конец дин. списка авторов
        }
    }
}

