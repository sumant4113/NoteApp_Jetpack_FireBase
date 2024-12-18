package com.example.crudjetpackcompose.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.crudjetpackcompose.Screens.InsertNotesScreen
import com.example.crudjetpackcompose.Screens.NotesScreen
import com.example.crudjetpackcompose.Screens.SplashScreen

@Composable
fun NotesNavigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "splash") {
        composable(NotesNavigationItem.SplashScreen.route) {
            SplashScreen(navHostController)
        }

        composable(NotesNavigationItem.HomeScreen.route) {
            NotesScreen(navHostController)
        }

        composable(NotesNavigationItem.InsertNotesScreen.route) {
            InsertNotesScreen()
        }
    }
}