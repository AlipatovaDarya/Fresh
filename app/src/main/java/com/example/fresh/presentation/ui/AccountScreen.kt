package com.example.fresh.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.fresh.R
import androidx.navigation.NavHostController
import com.example.fresh.presentation.ui.common.TopBar
import com.example.fresh.presentation.viewModels.AuthState
import com.example.fresh.presentation.viewModels.AuthViewModel
import com.example.fresh.presentation.viewModels.UserViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(navController: NavHostController, viewModelState: AuthViewModel) {
    val userViewModel = UserViewModel()

    userViewModel.getUserInfo(viewModelState.userLiveData.value?.uid)
    val userInfo = userViewModel.userInfoLiveData.observeAsState(null)
    val isEditing = remember { mutableStateOf(false) }
    val editedFirstName = remember { mutableStateOf(userInfo.value?.firstName ?: "") }
    val editedLastName = remember { mutableStateOf(userInfo.value?.lastName ?: "") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.beige)),
    ) {
        TopBar(
            currentScreen = "Мой аккаунт",
            arrowBack = true,
            navController = navController
        )
        Spacer(modifier = Modifier.height(20.dp))
        if (isEditing.value) {
            Column(
                modifier = Modifier.padding(horizontal = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = editedFirstName.value,
                    onValueChange = { editedFirstName.value = it },
                    label = { Text("Имя пользователя") },
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    //modifier = Modifier.padding(horizontal = 20.dp),
                    value = editedLastName.value,
                    onValueChange = { editedLastName.value = it },
                    label = { Text("Фамилия") },
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            userViewModel.updateAccFirstName(
                                editedFirstName.value,
                                userInfo.value?.uid ?: ""
                            )
                            userViewModel.updateAccLastName(
                                editedLastName.value,
                                userInfo.value?.uid ?: ""
                            )
                            isEditing.value = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.green_home)
                        ),
                    ) {
                        Text(text = "Сохранить изменения")
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Button(
                        onClick = {
                            isEditing.value = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.lilac)
                        ),
                    ) {
                        Text(text = "Отмена")
                    }
                }
            }
        } else {
            userInfo.value.let { user ->
                Text(
                    text = "Имя: ${user?.firstName}",
                    modifier = Modifier.padding(horizontal = 36.dp),
                    color = Color(R.color.black),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Фамилия: ${user?.lastName}",
                    modifier = Modifier.padding(horizontal = 36.dp),
                    color = Color(R.color.black)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { isEditing.value = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.green_home)
                ),
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            ) {
                Text(text = "Редактировать аккаунт")
            }
        }
        Spacer(modifier = Modifier.height(160.dp))
        //кнопки выхода и удаления акка
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = colorResource(id = R.color.beige),
                )
        ) {
            var deleteAcc by remember { mutableStateOf(false) }
            var logOut by remember { mutableStateOf(false) }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Button(
                    onClick = {
                        deleteAcc = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.dark_red)
                    )
                ) {
                    Text(text = "Удалить аккаунт")
                }
                Spacer(modifier = Modifier.width(12.dp))
                Button(
                    onClick = {
                        logOut = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.lilac)
                    )
                ) {
                    Text(text = "Выйти")
                }
            }


            var isDeletedAcc by remember { mutableStateOf(false) }
            var isLogedOut by remember { mutableStateOf(false) }
            val accState = viewModelState.authStateLiveData.observeAsState()
            if (deleteAcc) {
                AlertDialog(
                    onDismissRequest = { deleteAcc = false },
                    title = { Text("Удаление аккаунта") }, //Text("Удаление аккаунта")
                    text = { Text("Вы действительно хотите удалить аккаунт?") },
                    confirmButton = {
                        Button(
                            onClick = {
                                viewModelState.deleteAccount()
                                deleteAcc = false
                                isDeletedAcc = true
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.orange)
                            ),
                        ) {
                            Text("Удалить")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                deleteAcc = false
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


            if (logOut) {
                AlertDialog(
                    onDismissRequest = { logOut = false },
                    title = { Text("Выход из аккаунта") }, //Text("Удаление аккаунта")
                    text = { Text("Вы действительно хотите выйти из аккаунта?") },
                    confirmButton = {
                        Button(
                            onClick = {
                                viewModelState.logoutUser()
                                logOut = false
                                isLogedOut = true
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.orange)
                            ),
                        ) {
                            Text("Выйти")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                logOut = false
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
            if (accState.value == AuthState.UNAUTHENTICATED) {
                navController.navigate("autorisationScreen")
            }
        }
    }
}