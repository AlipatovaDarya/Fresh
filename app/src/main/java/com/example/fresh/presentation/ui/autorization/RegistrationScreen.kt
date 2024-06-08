package com.example.fresh.presentation.ui.autorization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fresh.R
import com.example.fresh.domain.models.User
import com.example.fresh.presentation.ui.common.MySnackBar
import com.example.fresh.presentation.ui.common.TopBar
import com.example.fresh.presentation.viewModels.AuthInputViewModel
import com.example.fresh.presentation.viewModels.AuthState
import com.example.fresh.presentation.viewModels.AuthViewModel
import com.example.fresh.presentation.viewModels.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navController: NavHostController, viewModelState: AuthViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.beige))
    ) {
        TopBar(
            currentScreen = stringResource(id = R.string.registration),
            arrowBack = true,
            navController = navController
        )

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            val inputViewModel = AuthInputViewModel()
            val isCorrectName = inputViewModel.isCorrectName.observeAsState(true)
            val isCorrectLastName = inputViewModel.isCorrectLastName.observeAsState(true)
            val isCorrectEmail = inputViewModel.isCorrectEmail.observeAsState(true)
            val isCorrectPassword1 = inputViewModel.isCorrectPassword1.observeAsState(true)
            val isCorrectPassword2 = inputViewModel.isCorrectPassword2.observeAsState(true)

            val email = remember { mutableStateOf("") }
            val name = remember { mutableStateOf("") }
            val lastName = remember { mutableStateOf("") }
            val password1 = remember { mutableStateOf("") }
            val password2 = remember { mutableStateOf("") }


            val isEnteredData = remember { mutableStateOf(false) }

            //Ввод email
            OutlinedTextField(
                value = email.value,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedLabelColor = colorResource(id = R.color.dark_gray),
                    unfocusedBorderColor = colorResource(id = R.color.dark_gray),
                    focusedLabelColor = colorResource(id = R.color.dark_gray),
                    focusedBorderColor = colorResource(id = R.color.dark_gray),
                    textColor = colorResource(id = R.color.dark_gray),
                    containerColor = colorResource(id = R.color.beige)
                ),
                onValueChange = {
                    email.value = it
                    //emailWasEntered.value = true
                },
                label = { Text(stringResource(id = R.string.email)) },
                placeholder = { Text("Введите email") },
                isError = isEnteredData.value && !isCorrectEmail.value,
                modifier = Modifier.padding(vertical = 10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                trailingIcon = {
                    if ((isEnteredData.value && !isCorrectEmail.value)) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "Введите email",
                            tint = Color.Red
                        )
                    }
                }
            )
//            if (!isCorrectEmail.value) {
//                Text("Введён некорректный email", color = Color.Red, modifier = Modifier.padding(start = 16.dp))
//            }
            inputViewModel.checkEmail(email.value)

            //поле ввода имени пользователя
            OutlinedTextField(
                value = name.value,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedLabelColor = colorResource(id = R.color.dark_gray),
                    unfocusedBorderColor = colorResource(id = R.color.dark_gray),
                    focusedLabelColor = colorResource(id = R.color.dark_gray),
                    focusedBorderColor = colorResource(id = R.color.dark_gray),
                    textColor = colorResource(id = R.color.dark_gray),
                    containerColor = colorResource(id = R.color.beige),
                ),
                onValueChange = {
                    name.value = it
                    //nameWasEntered.value = true
                },
                label = { Text("Имя") },
                placeholder = { Text("Введите имя") },
                isError = isEnteredData.value && !isCorrectName.value,
                modifier = Modifier.padding(vertical = 10.dp),
                trailingIcon = {
                    if ((isEnteredData.value && !isCorrectName.value)) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "Некорректный ввод",
                            tint = Color.Red
                        )
                    }
                },
            )
//            if (!isCorrectName.value) {
//                Text(stringResource(id = R.string.enter_user_name), color = Color.Red, modifier = Modifier.padding(start = 16.dp))
//            }
            inputViewModel.checkName(name.value)


            //поле ввода фамилии пользователя
            OutlinedTextField(
                value = lastName.value,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedLabelColor = colorResource(id = R.color.dark_gray),
                    unfocusedBorderColor = colorResource(id = R.color.dark_gray),
                    focusedLabelColor = colorResource(id = R.color.dark_gray),
                    focusedBorderColor = colorResource(id = R.color.dark_gray),
                    textColor = colorResource(id = R.color.dark_gray),
                    containerColor = colorResource(id = R.color.beige),
                ),
                onValueChange = {
                    lastName.value = it
                    //lastNameWasEntered.value = true
                },
                label = { Text("Фамилия") },
                placeholder = { Text("Введите фамилию") },
                isError = isEnteredData.value && !isCorrectLastName.value,
                modifier = Modifier.padding(vertical = 10.dp),
                trailingIcon = {
                    if ((isEnteredData.value && !isCorrectLastName.value)) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "Некорректный ввод",
                            tint = Color.Red
                        )
                    }
                },
            )
//            if (!isCorrectLastName.value) {
//                Text("Введите фамилию", color = Color.Red, modifier = Modifier.padding(start = 16.dp))
//            }
            inputViewModel.checkLastName(lastName.value)


            // ввод пароля
            OutlinedTextField(
                value = password1.value,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedLabelColor = colorResource(id = R.color.dark_gray),
                    unfocusedBorderColor = colorResource(id = R.color.dark_gray),
                    focusedLabelColor = colorResource(id = R.color.dark_gray),
                    focusedBorderColor = colorResource(id = R.color.dark_gray),
                    textColor = colorResource(id = R.color.dark_gray),
                    containerColor = colorResource(id = R.color.beige)
                ),
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                onValueChange = {
                    password1.value = it
                    //password1WasEntered.value = true
                },
                label = { Text(stringResource(id = R.string.password)) },
                placeholder = { Text(stringResource(id = R.string.enter_password)) },
                isError = isEnteredData.value && !isCorrectPassword1.value,
                modifier = Modifier.padding(vertical = 10.dp),
                trailingIcon = {
                    if ((isEnteredData.value && !isCorrectPassword1.value)) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "Введите пароль",
                            tint = Color.Red
                        )
                    }
                }
            )
//            if (!isCorrectPassword1.value) {
//                Text(stringResource(id = R.string.enter_password), color = Color.Red, modifier = Modifier.padding(start = 16.dp))
//            }
            inputViewModel.checkPassword1(password1.value)

            OutlinedTextField(
                value = password2.value,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedLabelColor = colorResource(id = R.color.dark_gray),
                    unfocusedBorderColor = colorResource(id = R.color.dark_gray),
                    focusedLabelColor = colorResource(id = R.color.dark_gray),
                    focusedBorderColor = colorResource(id = R.color.dark_gray),
                    textColor = colorResource(id = R.color.dark_gray),
                    containerColor = colorResource(id = R.color.beige)
                ),
                onValueChange = {
                    password2.value = it
                    //password2WasEntered.value = true
                },
                label = { Text(stringResource(id = R.string.confirm_password)) },
                placeholder = { Text(stringResource(id = R.string.confirm_password)) },
                isError = isEnteredData.value && !isCorrectPassword2.value,
                modifier = Modifier.padding(vertical = 10.dp),
                trailingIcon = {
                    if (isEnteredData.value && !isCorrectPassword2.value) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "Пароли не совпадают",
                            tint = Color.Red
                        )

                    }
                    /*if (!(isEnteredData.value && !isCorrectPassword1.value)) {
                        //Icon(Icons.Filled.Check, contentDescription = "", tint = Color.Green)
                    } else {
                        Icon(Icons.Filled.Close, contentDescription = "Пароли не совпадают", tint = Color.Red)
                    }*/
                },
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            )
//            if (!isCorrectPassword2.value) {
//                Text("Пароли не совпадают", color = Color.Red, modifier = Modifier.padding(start = 16.dp))
//            }
            inputViewModel.checkPassword2(password1.value, password2.value)

            //val authViewModel = viewModel<AuthViewModel>()
            val isCorrectAll = remember { mutableStateOf(true) }
            //var error = ""
            val userViewModel = UserViewModel()
            val correctUser = viewModelState.userLiveData.observeAsState()
            Button(
                modifier = Modifier.padding(top = 20.dp),
                onClick = {
                    isEnteredData.value = true

                    if (inputViewModel.isRegisterCorrect()) {
                        viewModelState.registerUser(email.value, password1.value)
                        if (correctUser.value?.uid != null) {
                            isCorrectAll.value = true
                            userViewModel.addUser(
                                User(
                                    viewModelState.userLiveData.value?.uid!!,
                                    name.value,
                                    lastName.value,
                                    email.value,
                                    AuthState.AUTHENTICATED,
                                    0L
                                )
                            )
                            navController.navigate("homeScreen")
                        }/* else {
                            isCorrectAll.value = false
                            viewModelState.deleteAccount()
                        }*/
                    } else {
                        //Log.e("МОЯ ОШИБКА", "Заполните корректно все поля")
                        //error = "Заполните корректно все поля"
                        isCorrectAll.value = false
                    }

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.orange)
                ),
            ) {
                Text(stringResource(id = R.string.to_register))
            }

            if (!isCorrectAll.value) {
                MySnackBar("Заполните корректно все поля")
            }


        }
    }
}