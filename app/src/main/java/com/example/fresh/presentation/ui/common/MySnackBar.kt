package com.example.fresh.presentation.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MySnackBar(str : String) {
    val snackbarVisible = remember { mutableStateOf(true) }

    if (snackbarVisible.value) {
        Snackbar(
            modifier = Modifier.padding(16.dp),
            action = {
                Button(onClick = { snackbarVisible.value = false }) {
                    Text(text = "ок")
                }
            }
        ) {
            Text(text = str)
        }
    }
}