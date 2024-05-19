package com.example.fresh.presentation.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fresh.R
import com.example.fresh.presentation.ui.common.TopBar
import com.example.fresh.presentation.viewModels.AuthViewModel
import com.example.fresh.presentation.viewModels.AuthorViewModel


@Composable
fun AuthorScreen(navController: NavHostController, viewModelState: AuthViewModel) {
    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.beige))
            .fillMaxSize()
    ) {
        TopBar(currentScreen = "Страница автора", arrowBack = true, navController = navController)

        val viewModel = AuthorViewModel()
        viewModel.getAuthorInfo(viewModelState.curPageIDLiveData.value.toString())
        val author = viewModel.authorInfoLiveData.observeAsState()
        if(author.value != null) {
            if(author.value!!.isExpert){
                viewModelState.curPageIDLiveData.value = "experts"
            } else{
                viewModelState.curPageIDLiveData.value = "authors"
            }
            Log.e(TAG, "author.value != null")
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "Фото автора",
                    modifier = Modifier
                        .size(300.dp)
                        .padding(end = 10.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = author.value?.name!!,
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = colorResource(id = R.color.dark_gray)
                    ),
                    modifier = Modifier.padding(bottom = 18.dp, start = 5.dp),
                )
                Text(
                    modifier = Modifier.padding(bottom = 18.dp),
                    text = author.value?.description!!,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.dark_gray)
                    ),
                )
            }

        } else{
            Log.e(TAG, "author.value == null")
        }


    }

}
