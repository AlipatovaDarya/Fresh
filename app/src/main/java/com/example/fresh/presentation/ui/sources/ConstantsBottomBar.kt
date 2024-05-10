package com.example.fresh.presentation.ui.sources

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List

object ConstantsBottomBar {
    val BottomNavItems = listOf(
        BottomNavItemModel(
            label = "QR-код",
            icon = Icons.Filled.List,
            route = "qrScreen"
        ),
        BottomNavItemModel(
            label = "Главная",
            icon = Icons.Filled.Home,
            route = "homeScreen"

        ),
        BottomNavItemModel(
            label = "Календарь",
            icon = Icons.Filled.DateRange,
            route = "calendarScreen"
        )
    )
}