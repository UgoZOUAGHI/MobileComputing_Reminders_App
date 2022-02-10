package com.example.mobilecomputing

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobilecomputing.ui.Home.Home
import com.example.mobilecomputing.ui.Login.Login
import com.example.mobilecomputing.ui.Login.Register
import com.example.mobilecomputing.ui.MobileComputingAppState
import com.example.mobilecomputing.ui.Reminders.Reminders
import com.example.mobilecomputing.ui.Reminders.ReminderViewModel
import com.example.mobilecomputing.ui.Profile.Profile
import com.example.mobilecomputing.ui.Profile.EditProfile
import com.example.mobilecomputing.ui.Reminders.EditReminder
import com.example.mobilecomputing.ui.Tutorial.Tutorial
import com.example.mobilecomputing.ui.Tutorial.Tutorial2
import com.example.mobilecomputing.ui.Tutorial.Tutorial3
import com.example.mobilecomputing.ui.rememberMobileComputingAppState

@Composable
fun MobileComputingApp(
    sharedPreferences: SharedPreferences,
    appState: MobileComputingAppState = rememberMobileComputingAppState()
) {
    NavHost(
        navController = appState.navController,
        startDestination = "login"
    ) {
        composable(route = "login") {
            Login(sharedPreferences,
                navController = appState.navController
            )
        }
        composable(route = "register") {
            Register(
                sharedPreferences,
                onBackPress = appState::navigateBack,
                navController = appState.navController
            )
        }
        composable(route = "home") {
            Home(navController = appState.navController)
        }
        composable(route = "reminders") {
            Reminders(onBackPress = appState::navigateBack,
                
                )
        }
        composable(route ="profile"){
            Profile(
                sharedPreferences,
                onBackPress= appState::navigateBack,
                navController = appState.navController)
            }
        composable(route = "editprofile") {
            EditProfile(
                sharedPreferences,
                onBackPress = appState::navigateBack,
                navController = appState.navController,
            )
        }
        composable(route ="tutorial"){
            Tutorial(onBackPress = appState::navigateBack,
                navController = appState.navController)
        }
        composable(route ="tutorial2"){
            Tutorial2(onBackPress = appState::navigateBack,
                navController = appState.navController)
        }
        composable(route ="tutorial3"){
            Tutorial3(onBackPress = appState::navigateBack,
                navController = appState.navController)
        }
        composable(route = "editReminder/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id")
            EditReminder(
                onBackPress = appState::navigateBack,
                reminderId = id ?: 0
            )
        }
    }
}
