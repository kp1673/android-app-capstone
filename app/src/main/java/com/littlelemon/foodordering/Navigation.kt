package com.littlelemon.foodordering

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController){

    val context = LocalContext.current
    val preferencesHandler = remember {
        PreferencesHandler(context)
    }
    NavHost(navController = navController,
        startDestination = if (preferencesHandler.getData("EMAIL", "").isBlank() ||
            preferencesHandler.getData("FIRST_NAME", "").isBlank() ||
            preferencesHandler.getData("LAST_NAME", "").isBlank())
            Onboarding.route else Home.route
    ){
        composable(Onboarding.route){
            Onboarding(navController)
        }
        composable(Home.route){
            Home(navController)
        }
        composable(Profile.route){
            Profile(navController)
        }
    }
}