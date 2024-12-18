package com.example.crudjetpackcompose.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crudjetpackcompose.ui.theme.ColorBlack
import com.example.crudjetpackcompose.ui.theme.ColorGrey
import com.example.crudjetpackcompose.ui.theme.ColorLiteGrey
import com.example.crudjetpackcompose.ui.theme.ColorRed
import com.google.firebase.firestore.FirebaseFirestore

@Composable
@Preview
fun InsertNotesScreen() {

    val db = FirebaseFirestore.getInstance()
    val notesDbRef = db.collection("notes")

    var tittle by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                contentColor = White,
                containerColor = ColorRed,
                shape = RoundedCornerShape(100.dp),
                onClick = { },
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Done",
                    tint = White

                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(ColorBlack)
        ) {

            Column(
                modifier = Modifier.padding(15.dp)
            ) {
                Text(
                    text = "Insert Notes", style = TextStyle(
                        fontSize = 32.sp,
                        color = White, fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))

                TextField(shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = ColorGrey,
                        unfocusedContainerColor = ColorGrey,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ), label = {
                        Text(
                            text = "Enter Title", style = TextStyle(
                                fontSize = 18.sp,
                                color = ColorLiteGrey
                            )
                        )
                    }, value = tittle, onValueChange = {
                        tittle = it
                    }, modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(15.dp))

                TextField(
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = ColorGrey,
                        unfocusedContainerColor = ColorGrey,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    label = {
                        Text(
                            text = "Enter Description",
                            style = TextStyle(
                                fontSize = 18.sp, color = ColorLiteGrey
                            )
                        )

                    }, value = description, onValueChange = {
                        description = it
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)
                )

            }


        }

    }

}