package com.example.fresh.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fresh.R
import com.example.fresh.presentation.ui.common.TopBar
import com.example.fresh.presentation.viewModels.AuthViewModel

@Composable
fun HomeScreen(navController: NavHostController, viewModelState: AuthViewModel) {
    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.beige))
    ) {

        TopBar("", false, navController)

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(color = colorResource(id = R.color.beige)),
        ) {
            // Первый ряд кнопок
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.dark_red)
                    ),
                    onClick = { navController.navigate("rankingListScreen") },
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
                        Image(
                            painter = painterResource(R.drawable.ranking_icon),
                            contentDescription = "Рейтинг",
                            //modifier = Modifier.height(45.dp)
                            modifier = Modifier.padding(bottom = 3.dp)
                        )
                        Text(text = stringResource(R.string.ranking), fontSize = 10.sp)
                    }
                }

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.green)
                    ),
                    onClick = {
                        viewModelState.curPageIDLiveData.value = "authors"
                        navController.navigate("authorListScreen")
                    },
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
                        Image(
                            painter = painterResource(R.drawable.author_icon),
                            contentDescription = "Ноты исполнителей",
                            //modifier = Modifier.height(45.dp)
                            modifier = Modifier.padding(bottom = 3.dp)
                        )
                        Text(text = stringResource(R.string.authors), fontSize = 10.sp)
                    }
                }
            }


            // Второй ряд кнопок
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
            )
            {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.rich_lilac)
                    ),
                    onClick = {
                        viewModelState.curPageIDLiveData.value = "experts"
                        navController.navigate("authorListScreen")
                    },
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
                        Image(
                            painter = painterResource(R.drawable.experts_icon),
                            contentDescription = "Шляпа эксперта",
                            modifier = Modifier
                                .width(62.dp)
                                .height((49.5).dp)
                                .padding(bottom = 3.dp),
                            contentScale = ContentScale.Crop
                        )
                        Text(text = stringResource(R.string.experts), fontSize = 10.sp)
                    }
                }

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.orange)
                    ),
                    onClick = { navController.navigate("eventsListScreen") },
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
                        Image(
                            painter = painterResource(R.drawable.events_icon),
                            contentDescription = "Доска мероприятий",
                            modifier = Modifier.padding(bottom = 3.dp)
                        )
                        Text(text = stringResource(R.string.events), fontSize = 10.sp)
                    }
                }
            }
            //конец второго ряда
        }
        //картинка
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painter = painterResource(R.drawable.home_picture),
                contentDescription = "Your Image",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }
    }

}
