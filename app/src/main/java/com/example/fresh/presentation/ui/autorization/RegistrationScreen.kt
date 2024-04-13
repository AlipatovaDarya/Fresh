package com.example.fresh.presentation.ui.autorization

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.fresh.R
import com.example.fresh.presentation.ui.common.TopBar
import com.example.fresh.presentation.viewModels.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.beige))
    ) {
        TopBar(
            currentScreen = stringResource(id = R.string.registration),
            arrowBack = true,
            moreVirtLogout = false,
            navController = navController
        )

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            val textEmail = remember { mutableStateOf("") }
            val isValidEmail = remember { mutableStateOf(false) }

            //поле ввода почты
            OutlinedTextField(
                value = textEmail.value,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedLabelColor = colorResource(id = R.color.dark_gray),
                    unfocusedBorderColor = colorResource(id = R.color.dark_gray),
                    focusedLabelColor = colorResource(id = R.color.dark_gray),
                    focusedBorderColor = colorResource(id = R.color.dark_gray),
                    textColor = colorResource(id = R.color.dark_gray),
                    containerColor = colorResource(id = R.color.beige)
                ),
                onValueChange = {
                    textEmail.value = it
                    isValidEmail.value = android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()
                },
                label = { Text(stringResource(id = R.string.email)) },
                placeholder = { Text("Введите email") },
                isError = !isValidEmail.value && textEmail.value.isNotEmpty(),
                modifier = Modifier.padding(vertical = 20.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                trailingIcon = {
                    if (textEmail.value.isNotEmpty() && !isValidEmail.value) {
                        Icon(Icons.Filled.Close, contentDescription = "Введите email", tint = Color.Red)
                    }
                }
            )
            if (!isValidEmail.value && textEmail.value.isNotEmpty()) {
                Text("Введён некорректный email", color = Color.Red, modifier = Modifier.padding(start = 16.dp))
            }


            val textUserName = remember { mutableStateOf("") }
            val isValidUserName = remember { mutableStateOf(false) }

            /*//поле ввода имени пользователя
            OutlinedTextField(
                value = textUserName.value,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedLabelColor = colorResource(id = R.color.dark_gray),
                    unfocusedBorderColor = colorResource(id = R.color.dark_gray),
                    focusedLabelColor = colorResource(id = R.color.dark_gray),
                    focusedBorderColor = colorResource(id = R.color.dark_gray),
                    textColor = colorResource(id = R.color.dark_gray),
                    containerColor = colorResource(id = R.color.beige),
                ),
                onValueChange = {
                    textUserName.value = it
                    isValidUserName.value = textUserName.value.isNotEmpty()
                },
                label = {Text(stringResource(id = R.string.user_name),)},
                placeholder = { Text(stringResource(id = R.string.enter_user_name),)},
                modifier = Modifier.padding(vertical = 20.dp),
                trailingIcon = {
                    if (!isValidUserName.value && textUserName.value.isEmpty()) {
                        Icon(Icons.Filled.Close, contentDescription = "Некорректный ввод", tint = Color.Red)
                    }
                },
            )
            if (!isValidUserName.value && textUserName.value.isNotEmpty()) {
                Text(stringResource(id = R.string.enter_user_name), color = Color.Red, modifier = Modifier.padding(start = 16.dp))
            }*/


            val textPassword = remember { mutableStateOf("") }
            val isValidPassword = remember { mutableStateOf(false) }

            // ввод пароля
            OutlinedTextField(
                value = textPassword.value,
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
                    textPassword.value = it
                    isValidPassword.value = textPassword.value.isNotEmpty()
                },
                label = { Text(stringResource(id = R.string.password)) },
                placeholder = { Text(stringResource(id = R.string.enter_password)) },
                isError = !isValidPassword.value && textPassword.value.isNotEmpty(),
                modifier = Modifier.padding(vertical = 20.dp),
                trailingIcon = {
                    if (textPassword.value.isNotEmpty() && !isValidPassword.value) {
                        Icon(Icons.Filled.Close, contentDescription = "Введите имя пользователя", tint = Color.Red)
                    }
                }
            )
            if (!isValidPassword.value && textPassword.value.isNotEmpty()) {
                Text(stringResource(id = R.string.enter_password), color = Color.Red, modifier = Modifier.padding(start = 16.dp))
            }

            val textPassword2 = remember { mutableStateOf("") }
            val isValidPassword2 = remember { mutableStateOf(false) }

            /*OutlinedTextField(
                value = textPassword2.value,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedLabelColor = colorResource(id = R.color.dark_gray),
                    unfocusedBorderColor = colorResource(id = R.color.dark_gray),
                    focusedLabelColor = colorResource(id = R.color.dark_gray),
                    focusedBorderColor = colorResource(id = R.color.dark_gray),
                    textColor = colorResource(id = R.color.dark_gray),
                    containerColor = colorResource(id = R.color.beige)
                ),
                onValueChange = {
                    textPassword2.value = it
                    isValidPassword2.value = textPassword2.value.equals(textPassword.value)
                },
                label = {Text(stringResource(id = R.string.confirm_password))},
                placeholder = { Text(stringResource(id = R.string.enter_password2)) },
                isError = !isValidPassword2.value && textPassword2.value.isNotEmpty(),
                modifier = Modifier.padding(vertical = 20.dp),
                trailingIcon = {
                    if (isValidPassword2.value) {
                        Icon(Icons.Filled.Check, contentDescription = "", tint = Color.Green)
                    } else if (textPassword2.value.isNotEmpty()) {
                        Icon(Icons.Filled.Close, contentDescription = "Пароли не совпадают", tint = Color.Red)
                    }
                },
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            )
            if (!isValidPassword2.value && textPassword2.value.isNotEmpty()) {
                Text("Пароли не совпадают", color = Color.Red, modifier = Modifier.padding(start = 16.dp))
            }*/

            val authViewModel = viewModel<AuthViewModel>()

            Button(
                modifier = Modifier.padding(top = 20.dp),
                onClick = {
                    authViewModel.registerUser(textEmail.value, textPassword.value)

                    /*var flag = true
                    if (!textUserName.value.isNotEmpty()) {
                        flag = false
                    }
                    if (!isValidPassword.value){
                        flag = false
                    }
                    if (!isValidEmail.value){
                        flag = false
                    }
                    if (!isValidPassword2.value){
                        flag = false
                    }
                    if(flag){*/
                    navController.navigate("homeScreen")
                    //}
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.orange)
                ),

            ) {
                Text(stringResource(id = R.string.to_register))
            }

        }

    }
}