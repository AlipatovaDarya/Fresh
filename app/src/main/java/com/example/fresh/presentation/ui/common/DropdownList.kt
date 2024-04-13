package com.example.fresh.presentation.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.fresh.R


@Composable
fun DropdownList() {
    //начало выпадающего спика
    var expanded by remember { mutableStateOf(false) }
    var showDialogLogout by remember  {mutableStateOf(false)}
    var showDialogDropAcc by remember  {mutableStateOf(false)}

    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier.padding(end = 20.dp)
    ) {
        Box(modifier = Modifier
            .padding(end = 10.dp)
        ) {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Выход или удаление аккаунта")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Выйти") },
                    onClick = {
                        showDialogLogout = true
                    }
                )
                DropdownMenuItem(
                    text = { Text("Удалить аккаунт") },
                    onClick = { showDialogDropAcc = true}
                )
            }
        }
    }
    //конец выпадающего списка


    //диалоговое окно для выхода из аккаунта
    if(showDialogLogout){
        AlertDialog(
            onDismissRequest =  {showDialogLogout = false} ,
            title =  {Text("Выход из аккаунта")} ,
            text = {Text("Вы действительно хотите выйти?")},
            confirmButton = {
                Button(
                    onClick = {
                        // Действия при подтверждении выхода из аккаунта
                        showDialogLogout = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor  = colorResource(id = R.color.orange)
                    ),
                ) {
                    Text("Выйти")
                }
            }                ,
            dismissButton = {
                Button(
                    onClick = {
                        showDialogLogout = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor  = colorResource(id = R.color.lilac)
                    ),
                ) {
                    Text("Отмена")
                }
            }
        )
    }

    //диалоговое окно для удаления аккаунта
    if(showDialogDropAcc){
        AlertDialog(
            onDismissRequest =  {showDialogDropAcc = false} ,
            title =  {Text("Удаление аккаунта")} ,
            text = {Text("Вы действительно хотите удалить аккаунт?")},
            confirmButton = {
                Button(
                    onClick = {
                        // Действия при подтверждении выхода из аккаунта
                        showDialogDropAcc = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor  = colorResource(id = R.color.orange)
                    ),
                ) {
                    Text("Удалить")
                }
            }                ,
            dismissButton = {
                Button(
                    onClick = {
                        showDialogDropAcc = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor  = colorResource(id = R.color.lilac)
                    ),
                ) {
                    Text("Отмена")
                }
            }
        )
    }

}


