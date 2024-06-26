package com.example.fresh.presentation.ui.autorization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fresh.R
import com.example.fresh.presentation.ui.common.MySnackBar
import com.example.fresh.presentation.ui.common.TopBar
import com.example.fresh.presentation.viewModels.AuthViewModel

@Composable
fun AutorisationScreen(navController: NavHostController, viewModelState: AuthViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.beige))
    ) {
        val isDeletedAcc = viewModelState.accDeleted.observeAsState()

        TopBar(
            currentScreen = stringResource(id = R.string.authorization),
            arrowBack = false,
            navController = navController
        )

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    navController.navigate("loginScreen")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.orange)
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(top = 40.dp, bottom = 24.dp)
                    .size(width = 320.dp, height = 50.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.log_in),
                    style = TextStyle(color = colorResource(id = R.color.white), fontSize = 16.sp)
                )
            }
            Button(
                onClick = {
                    navController.navigate("registrationScreen")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.orange)
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .size(width = 320.dp, height = 50.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.registration),
                    style = TextStyle(color = colorResource(id = R.color.white), fontSize = 16.sp)
                )
            }

        }
        if(isDeletedAcc.value != null){
            if(isDeletedAcc.value!!){
                MySnackBar(str = "Аккаунт удалён")
            }
        }

    }
}

