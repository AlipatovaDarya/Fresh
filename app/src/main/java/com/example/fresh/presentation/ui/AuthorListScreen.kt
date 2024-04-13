package com.example.fresh.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun AuthorListScreen(navController: NavHostController, Authors: List<String>) {
    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.beige))
    ) {
        TopBar("Авторы", true, true, navController)

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            //начало дин. списка авторов
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                items(Authors) { item ->
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
                                text = item,
                                style = TextStyle(
                                    fontSize = 14.sp, color = colorResource(id = R.color.white)
                                )
                            )
                        }
                    }

                }
            }
            //конец дин. списка авторов
        }
    }
}