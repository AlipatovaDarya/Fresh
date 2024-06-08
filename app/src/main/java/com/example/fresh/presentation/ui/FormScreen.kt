package com.example.fresh.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.fresh.domain.models.Answer
import com.example.fresh.domain.models.GeneralAnswer
import com.example.fresh.domain.models.Vote
import com.example.fresh.presentation.ui.common.TopBar
import com.example.fresh.presentation.viewModels.AuthViewModel
import com.example.fresh.presentation.ui.common.MySnackBar
import com.example.fresh.presentation.viewModels.AuthorViewModel
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
        viewModel.getFormQuestions(viewModelState.curItemIDLiveData.value)
        val questions = viewModel.questionsLiveData.observeAsState()
        //val isCorrectEnter = viewModel.isCorrectEnter.observeAsState()

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
            val resAnswers: MutableList<Answer?> = mutableListOf()
            val resVotes: MutableList<Vote?> = mutableListOf()
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                if(questions.value != null){
                    items(questions.value!!) { item ->
                        val authorViewModel = AuthorViewModel()
                        val answer = remember { mutableStateOf("") }
                        if(item?.hasMarks == true){
                            authorViewModel.getAuthorInfo(item.questionText)
                            val expanded = remember { mutableStateOf(false) }
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text("Оцени ${item.authorName}")
                                OutlinedTextField(
                                    value = answer.value,
                                    onValueChange = { },
                                    label = { Text("Выбери число из списка") },
                                    readOnly = true,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp, horizontal = 16.dp),
                                    trailingIcon = {
                                        IconButton(onClick = { expanded.value = !expanded.value }) {
                                            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                                        }
                                    }
                                )
                                DropdownMenu(
                                    expanded = expanded.value,
                                    onDismissRequest = { expanded.value = false },
                                    modifier = Modifier.width(200.dp)
                                ) {
                                    (1..10).forEach { number ->
                                        DropdownMenuItem(
                                            onClick = {
                                                answer.value = number.toString()
                                                expanded.value = false
                                            },
                                            text = { Text("$number") }
                                        )
                                    }
                                }
                            }
                        } else if (item?.hasMarks == false){
                            OutlinedTextField(
                                value = answer.value,
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    unfocusedLabelColor = colorResource(id = R.color.dark_gray),
                                    unfocusedBorderColor = colorResource(id = R.color.dark_gray),
                                    focusedLabelColor = colorResource(id = R.color.dark_gray),
                                    focusedBorderColor = colorResource(id = R.color.dark_gray),
                                    textColor = colorResource(id = R.color.dark_gray),
                                    containerColor = colorResource(id = R.color.beige)
                                ),
                                onValueChange = {
                                    answer.value = it
                                },
                                label = { item.questionText?.let { Text(it) } },
                                placeholder = { Text("Введите ответ") },
                                modifier = Modifier.padding(vertical = 10.dp),
                            )
                        }
                        if(item?.hasMarks == true && answer.value.isNotEmpty()){
                            resVotes.add(Vote(viewModelState.userLiveData.value?.uid, answer.value.toInt(), viewModelState.curItemIDLiveData.value, item.questionText))
                        } else if(item?.hasMarks == false && answer.value.isNotEmpty()){
                            resAnswers.add(Answer(item.id, answer.value, viewModelState.userLiveData.value?.uid, viewModelState.curItemIDLiveData.value) )
                        }
                        /*if (item != null) {
                            res.add(item.hasMarks?.let { GeneralAnswer(it, answerText.value) })
                        }*/

                    }
                }
            }

            Button(
                modifier = Modifier.padding(top = 20.dp),
                onClick = {
                    viewModel.addAnswers(resAnswers)
                    viewModel.addVotes(resVotes)
                    navController.navigate("homeScreen")
                    /*if(viewModel.isEnterCorrect(res)){
                    } else{
                        viewModel.isCorrectEnter.value = false
                    }*/
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.orange)
                ),
            ) {
                Text("Отправить ответы")
            }
            /*if(!isCorrectEnter.value!!){
                MySnackBar("Заполните корректно все поля")
            }*/

        }


    }
}