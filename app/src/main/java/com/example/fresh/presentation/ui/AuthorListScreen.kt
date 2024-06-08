package com.example.fresh.presentation.ui

import android.content.ContentValues.TAG
import android.util.Log
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
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.example.fresh.R
import com.example.fresh.domain.models.Author
import com.example.fresh.presentation.ui.common.TopBar
import com.example.fresh.presentation.viewModels.AuthViewModel
import com.example.fresh.presentation.viewModels.AuthorViewModel

@Composable
fun AuthorListScreen(
    navController: NavHostController,
    viewModelState: AuthViewModel
) {
    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.beige))
    ) {
        val authors : State<List<Author?>?>
        val viewModel = AuthorViewModel()
        val pageName: String
        if(viewModelState.curPageLiveData.value == "authors"){
            pageName = "Авторы"
            viewModel.getAuthors()
            authors = viewModel.authorsLiveData.observeAsState()
        } else if(viewModelState.curPageLiveData.value == "experts"){
            pageName = "Эксперты"
            viewModel.getExperts()
            authors = viewModel.expertsLiveData.observeAsState()
        } else{
            pageName = ""
            authors = MutableLiveData<List<Author?>?>().observeAsState()
        }
        TopBar(pageName, true, navController)

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                if(authors.value != null){
                    items(authors.value!!) { item ->
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = colorResource(id = R.color.orange)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 10.dp)
                                .clickable {
                                    viewModelState.curItemIDLiveData.value = item?.id
                                    //Yb5CyY3RUIdK2HStz5Is
                                    //viewModelState.curPageIDLiveData.value = "Yb5CyY3RUIdK2HStz5Is"
                                    Log.e(TAG, item?.id!!)
                                    navController.navigate("authorScreen")
                                },
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(
                                    text = item?.name!!,
                                    style = TextStyle(
                                        fontSize = 14.sp, color = colorResource(id = R.color.white)
                                    )
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}