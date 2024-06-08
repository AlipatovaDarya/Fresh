package com.example.fresh.presentation.ui

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
/*import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage*/
import com.example.fresh.R
import com.example.fresh.domain.models.Author
import com.example.fresh.presentation.ui.common.TopBar
import com.example.fresh.presentation.viewModels.AuthViewModel
import com.example.fresh.presentation.viewModels.AuthorViewModel
import com.example.fresh.presentation.viewModels.ViewModelImage


//@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AuthorScreen(navController: NavHostController, viewModelState: AuthViewModel) {
    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.beige))
            .fillMaxSize()
    ) {
        TopBar(currentScreen = "Страница автора", arrowBack = true, navController = navController)
        val author: State<Author?>
        val viewModel = AuthorViewModel()
        viewModel.getAuthorInfo(viewModelState.curItemIDLiveData.value)
        author = viewModel.authorInfoLiveData.observeAsState()
        if (author.value != null) {
            if (author.value!!.isExpert) {
                viewModelState.curPageLiveData.value = "experts"
            } else {
                viewModelState.curPageLiveData.value = "authors"
            }
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val viewModelImage = ViewModelImage()
                viewModelImage.getImageUri("authors/${viewModelState.curItemIDLiveData.value}.png")
                val imageUri = viewModelImage.imageUri.observeAsState(initial = "")
                if (imageUri.value?.isNotEmpty() == true) {
                    AsyncImage(
                        model = imageUri.value,
                        contentDescription = "author image",
                        modifier = Modifier
                            .padding(20.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 40.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,
                    ) {
                        (if (author.value?.name != null) author.value?.name else "")?.let {
                            Text(
                                text = it,
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    color = colorResource(id = R.color.dark_gray)
                                ),
                                modifier = Modifier.padding(bottom = 18.dp, start = 5.dp),
                            )
                        }

                        (if (author.value?.description != null) author.value?.description else {
                            ""
                        })?.let {
                            Text(
                                modifier = Modifier.padding(bottom = 18.dp),
                                text = it,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = colorResource(id = R.color.dark_gray)
                                ),
                            )
                        }
                        viewModel.getAuthorAudioProfiles(author.value!!.id)
                        val audioProfiles = viewModel.audioProfile.observeAsState()
                        LazyColumn(){
                            if(audioProfiles.value != null){
                                Log.e(TAG, audioProfiles.value.toString())
                                items(audioProfiles.value!!){ item ->
                                    Row(modifier = Modifier.padding(8.dp)) {
                                        val uriHandler = LocalUriHandler.current
                                        Text("${item?.platform}: ",
                                            style = TextStyle(
                                                fontSize = 12.sp,
                                                color = Color.Blue
                                            ),
                                            modifier = Modifier
                                                .clickable {
                                                    item?.link?.let { uriHandler.openUri(it) }
                                                })
                                        /*Text("${item?.link}: ",

                                        )*/

                                    }
                                }
                            }

                        }

                    }

                }
            }

        }
    }
}
