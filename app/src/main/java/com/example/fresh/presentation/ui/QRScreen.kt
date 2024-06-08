package com.example.fresh.presentation.ui

import android.Manifest
import android.content.Intent
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fresh.R
import com.example.fresh.presentation.ui.common.MySnackBar
import com.example.fresh.presentation.viewModels.AuthViewModel
import com.example.fresh.presentation.viewModels.FormViewModel
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Composable
fun QRScreen(
    navController: NavHostController,
    viewModelState: AuthViewModel,
) {
    val context = LocalContext.current
    val dataReceived = remember { mutableStateOf(false) }
    val data = remember { mutableStateOf("") }
    val hasCameraPermission = remember { mutableStateOf(false) }
    val viewModel = FormViewModel()
    viewModel.getCurrentEventID(viewModelState.userLiveData.value?.uid)
    //val eventID = viewModel.curEvent.observeAsState()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        hasCameraPermission.value = isGranted
    }

    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { result ->
            if (result != null) {
                //data.value = result.contents.toString()
                viewModelState.curItemIDLiveData.value = result.contents.toString()
                dataReceived.value = true
                //if(eventID.value == data.value){
                navController.navigate("formScreen")
                //}
            }
        }
    )

    val options = ScanOptions()
    options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
    options.setPrompt("Наведите камеру на QR-код")
    options.setCameraId(0)
    options.setBeepEnabled(false)
    options.setBarcodeImageEnabled(true)

    SideEffect {
        // Запрашиваем разрешение на камеру при запуске экрана
        launcher.launch(Manifest.permission.CAMERA)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (hasCameraPermission.value) {
            Button(
                onClick = { scanLauncher.launch(options) },
                colors = ButtonDefaults.buttonColors(
                    containerColor  = colorResource(id = R.color.rich_lilac)
                ),
                ) {
                Text("Сканировать QR-код")
            }
        } else {
            // Если нет разрешения, выводим сообщение с просьбой предоставить его
            Text("Для сканирования QR-кода необходимо разрешение на использование камеры")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { launcher.launch(Manifest.permission.CAMERA) }) {
                Text("Запросить разрешение")
            }
        }

        /*if (dataReceived.value) {
            MySnackBar(str = data.value)
        }*/
    }
    /*val onBackPressedCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (navController.currentBackStackEntry?.destination?.route == "formScreen") {
                    navController.navigate("homeScreen")
                } *//*else {

                }*//*
            }
        }
    }

    val dispatcher = LocalOnBackPressedDispatcherOwner.current
    SideEffect {
        dispatcher?.onBackPressedDispatcher?.addCallback(onBackPressedCallback)
    }

    BackHandler(enabled = true) {
        onBackPressedCallback.isEnabled = true
    }*/
}

fun checkData(s: String) {

}