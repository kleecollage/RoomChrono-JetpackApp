package com.example.roomchronoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.roomchronoapp.viewModels.ChronosViewModel
import com.example.roomchronoapp.viewModels.CronometroViewModel
import com.example.roomchronoapp.views.AddView
import com.example.roomchronoapp.views.EditView
import com.example.roomchronoapp.views.HomeView

@Composable
fun NavManager(cronometroVM: CronometroViewModel, chronosVM: ChronosViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(navController, chronosVM)
        }
        composable("AddView"){
            AddView(navController, cronometroVM, chronosVM)
        }
        composable("EditView/{id}", arguments = listOf(
            navArgument("id") {type = NavType.LongType}
        )){
            val id = it.arguments?.getLong("id") ?: 0
            EditView(navController, cronometroVM, chronosVM, id)
        }
    }
}