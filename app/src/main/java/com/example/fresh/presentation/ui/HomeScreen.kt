package com.example.fresh.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fresh.R
import com.example.fresh.presentation.ui.common.TopBar

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.beige))
    ){

        TopBar("", false, true, navController)

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.beige)),

            ) {


            // Первый ряд кнопок
            Row (verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)

            )
            {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor  = colorResource(id = R.color.orange)
                    ),
                    onClick = {navController.navigate("rankingListScreen")},
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(start = 40.dp, end = 20.dp)
                        .size(120.dp)
                )
                {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Icon(imageVector = Icons.Default.List, contentDescription = "Рейтинги", modifier = Modifier.size(70.dp))
                        Text(text = stringResource(R.string.ranking), fontSize = 10.sp)
                    }
                }

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor  = colorResource(id = R.color.turquoise)
                    ),
                    onClick = {navController.navigate("authorListScreen")},
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(start = 20.dp, end = 40.dp)
                        .size(120.dp)
                )
                {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Icon(imageVector = Icons.Default.List, contentDescription = "Авторы", modifier = Modifier.size(70.dp))
                        Text(text = stringResource(R.string.authors), fontSize = 10.sp)
                    }
                }
            }


            // Второй ряд кнопок
            Row (verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)

            )
            {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor  = colorResource(id = R.color.light_pink)
                    ),
                    onClick = {navController.navigate("authorListScreen")},
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(start = 40.dp, end = 20.dp)
                        .size(120.dp)
                )
                {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Icon(imageVector = Icons.Default.List, contentDescription = "Эксперты", modifier = Modifier.size(70.dp))
                        Text(text = stringResource(R.string.experts), fontSize = 10.sp)
                    }
                }

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor  = colorResource(id = R.color.green_home)
                    ),
                    onClick = {navController.navigate("eventsListScreen")},
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(start = 20.dp, end = 40.dp)
                        .size(120.dp)
                )
                {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Icon(imageVector = Icons.Default.List, contentDescription = "Мероприятия", modifier = Modifier.size(70.dp))
                        Text(text = stringResource(R.string.events), fontSize = 10.sp)
                    }
                }
            }
            //конец второго ряда

            //картинка
            val imagePainter = painterResource(id = R.drawable.orange)

            Image(painter = imagePainter, contentDescription = "Картинка главная", modifier = Modifier.padding(top = 40.dp, bottom = 80.dp))

        }



    }







}
