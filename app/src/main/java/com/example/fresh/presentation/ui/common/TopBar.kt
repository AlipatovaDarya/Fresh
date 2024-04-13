package com.example.fresh.presentation.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fresh.R
import com.example.fresh.presentation.ui.common.DropdownList

@Composable
fun TopBar(currentScreen : String, arrowBack: Boolean, moreVirtLogout: Boolean, navController: NavHostController){

    Row (verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 30.dp, start = 24.dp)
    ) {
        Column {
            Icon(imageVector = Icons.Default.Star, contentDescription = "Иконка приложения", modifier = Modifier
                .size(50.dp)
                .padding(end = 10.dp),
                tint = colorResource(id = R.color.dark_gray),
            )
            if (arrowBack){
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Назад",
                    modifier = Modifier
                        .padding(top = 10.dp, start = 5.dp)
                        .clickable {
                            navController.popBackStack()
                        },
                    tint = colorResource(id = R.color.dark_gray),
                )
            }

        }


        Column {
            Row {
                Text(text = stringResource(id = R.string.app_name_r),
                    style = TextStyle(fontSize = 26.sp, color = colorResource(id = R.color.dark_gray)),
                    modifier = Modifier.padding(top = 3.dp, bottom = 2.dp, start = 3.dp)
                )
                Spacer(modifier = Modifier.width(200.dp))
                if(moreVirtLogout){
                    DropdownList()
                }
            }

            Text(text = currentScreen,
                style = TextStyle(fontSize = 18.sp, color = colorResource(id = R.color.dark_gray))
            )
        }
    }
    // конец топ бара

}