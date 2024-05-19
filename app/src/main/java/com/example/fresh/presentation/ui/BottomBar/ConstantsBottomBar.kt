package com.example.fresh.presentation.ui.BottomBar

import com.example.fresh.R

object ConstantsBottomBar {
    val BottomNavItems = listOf(
        BottomNavItemModel(
            label = "QR-код",
            icon = R.drawable.qr_icon,
            route = "qrScreen"
        ),
        BottomNavItemModel(
            label = "Главная",
            icon = R.drawable.home_icon,
            route = "homeScreen"

        ),
        BottomNavItemModel(
            label = "Аккаунт",
            icon = R.drawable.account_icon,
            route = "accountScreen"
        )
    )
}