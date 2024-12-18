package com.example.crudjetpackcompose.Screens

import android.graphics.Color
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.crudjetpackcompose.Navigation.NotesNavigationItem
import com.example.crudjetpackcompose.R
import com.example.crudjetpackcompose.ui.theme.ColorBlack
import kotlinx.coroutines.delay

@Composable
//@Preview
fun SplashScreen(navHostController: NavHostController) {

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = ColorBlack)
        ) {

            Image(
                painter = painterResource(id = R.drawable.firebase_logo),
                contentDescription = "logo",
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.Center)
            )
        }
    }

    LaunchedEffect(Unit) {
        delay(2500)
        navHostController.navigate(NotesNavigationItem.HomeScreen.route) {
            popUpTo(NotesNavigationItem.SplashScreen.route) {
                inclusive = true
            }
        }
    }
}