package com.example.fresh


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fresh.domain.models.Event
import com.example.fresh.presentation.ui.BottomBar.ConstantsBottomBar
import com.example.fresh.presentation.ui.EventScreen
import com.example.fresh.presentation.ui.AuthorListScreen
import com.example.fresh.presentation.ui.AuthorScreen
import com.example.fresh.ui.theme.FreshTheme
import com.example.fresh.presentation.ui.AccountScreen
import com.example.fresh.presentation.ui.EventsListScreen
import com.example.fresh.presentation.ui.FormScreen
import com.example.fresh.presentation.ui.HomeScreen
import com.example.fresh.presentation.ui.QRScreen
import com.example.fresh.presentation.ui.RankingScreen
import com.example.fresh.presentation.ui.autorization.AutorisationScreen
import com.example.fresh.presentation.ui.autorization.LoginScreen
import com.example.fresh.presentation.ui.autorization.RegistrationScreen
import com.example.fresh.presentation.viewModels.AuthViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.firebase.Timestamp


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FreshTheme {
                val navController: NavHostController = rememberAnimatedNavController()
                val isShowBottomBar = remember { mutableStateOf(false) }
                val viewModelState = AuthViewModel()


                Surface(color = MaterialTheme.colorScheme.surface) {
                    Scaffold(
                        bottomBar = {
                            if (isShowBottomBar.value)
                                BottomNavigationBar(navController = navController)
                        },
                        content = { padding ->
                            AppScreen(
                                isShowBottomBar = isShowBottomBar,
                                navController = navController,
                                padding = padding,
                                viewModelState = viewModelState,
                            )
                        }
                    )
                }
            }
        }
    }


}


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar(
        containerColor = colorResource(id = R.color.green),
        contentColor = colorResource(id = R.color.lilac),
        tonalElevation = 60.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        ConstantsBottomBar.BottomNavItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = { navController.navigate(navItem.route) },
                icon = {
                    Image(
                        painter = painterResource(id = navItem.icon),
                        contentDescription = navItem.label,
                        modifier = Modifier
                            .height(23.dp)
                            .width(23.dp),
                        contentScale = ContentScale.Crop
                    )
                },
                label = { Text(text = navItem.label) },
                alwaysShowLabel = false
            )
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppScreen(
    navController: NavHostController,
    padding: PaddingValues,
    isShowBottomBar: MutableState<Boolean>,
    viewModelState: AuthViewModel,
) {
    AnimatedNavHost(
        modifier = Modifier.padding(paddingValues = padding),
        navController = navController,
        startDestination = "autorisationScreen",
        builder = {
            composable(route = "autorisationScreen") {
                AutorisationScreen(navController = navController, viewModelState)
                isShowBottomBar.value = false
            }
            composable(route = "loginScreen") {
                LoginScreen(navController = navController, viewModelState)
                isShowBottomBar.value = false
            }
            composable(route = "registrationScreen") {
                RegistrationScreen(navController = navController, viewModelState)
                isShowBottomBar.value = false
            }
            composable(route = "homeScreen") {
                HomeScreen(navController = navController, viewModelState)
                isShowBottomBar.value = true
            }
            composable(route = "accountScreen") {
                AccountScreen(navController, viewModelState)
                isShowBottomBar.value = true
            }
            composable(route = "qrScreen") {
                QRScreen(navController, viewModelState)
                isShowBottomBar.value = true
            }
            composable(route = "rankingListScreen") {
                RankingScreen(
                    navController = navController,
                    viewModelState
                )
                isShowBottomBar.value = true
            }
            composable(route = "authorListScreen", arguments = emptyList()) {
                AuthorListScreen(
                    navController = navController,
                    viewModelState
                )
                isShowBottomBar.value = true
            }
            composable(route = "authorScreen") {
                AuthorScreen(
                    navController = navController,
                    viewModelState,
                    //Author(id="id", name="имя", description = "описание", isExpert = false, score = 0),
                )
                isShowBottomBar.value = true
            }
            composable(route = "eventScreen") {
                EventScreen(
                    navController = navController,
                    viewModelState,
                )
                isShowBottomBar.value = true
            }
            composable(route = "eventsListScreen") {
                EventsListScreen(
                    navController = navController,
                    viewModelState
                )
                isShowBottomBar.value = true
            }
            composable(route = "formScreen") {
                FormScreen(
                    navController = navController,
                    viewModelState
                )
                isShowBottomBar.value = true
            }
        })
}


/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FreshTheme {
        //Greeting("Android")
    }
}*/

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "name!",
        modifier = modifier
    )
}*/