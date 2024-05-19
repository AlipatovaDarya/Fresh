package com.example.fresh.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fresh.R
import com.example.fresh.domain.models.GeneralAnswer
import com.example.fresh.presentation.ui.common.TopBar
import com.example.fresh.presentation.viewModels.AuthViewModel
import com.example.fresh.presentation.ui.common.MySnackBar
import com.example.fresh.presentation.viewModels.FormViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(navController: NavHostController, viewModelState: AuthViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.beige))
    ) {
        val viewModel = FormViewModel()
        viewModel.getFormQuestions(viewModelState.curPageIDLiveData.value)
        val questions = viewModel.questionsLiveData.observeAsState()
        val isCorrectEnter = viewModel.isCorrectEnter.observeAsState()

        TopBar(
            currentScreen = "Форма обратной связи",
            arrowBack = false,
            navController = navController
        )
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            val res: MutableList<GeneralAnswer?> = mutableListOf()
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                if(questions.value != null){
                    items(questions.value!!) { item ->
                        val answerText = remember { mutableStateOf("") }
                        OutlinedTextField(
                            value = answerText.value,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedLabelColor = colorResource(id = R.color.dark_gray),
                                unfocusedBorderColor = colorResource(id = R.color.dark_gray),
                                focusedLabelColor = colorResource(id = R.color.dark_gray),
                                focusedBorderColor = colorResource(id = R.color.dark_gray),
                                textColor = colorResource(id = R.color.dark_gray),
                                containerColor = colorResource(id = R.color.beige)
                            ),
                            onValueChange = {
                                answerText.value = it
                            },
                            label = { Text(stringResource(id = R.string.email)) },
                            placeholder = { Text("Введите email") },
                            modifier = Modifier.padding(vertical = 10.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        )
                        if (item != null) {
                            res.add(item.hasMarks?.let { GeneralAnswer(it, answerText.value) })
                        }
                    }
                }
            }
            Button(
                modifier = Modifier.padding(top = 20.dp),
                onClick = {

                    if(viewModel.isEnterCorrect(res)){
                        viewModel.addAnswers(res)

                    } else{
                        viewModel.isCorrectEnter.value = false
                    }

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.orange)
                ),
            ) {
                Text("Отправить ответы")
            }

            if(!isCorrectEnter.value!!){
                MySnackBar("Заполните корректно все поля")
            }

        }


    }
}