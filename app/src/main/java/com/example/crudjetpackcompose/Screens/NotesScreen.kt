package com.example.crudjetpackcompose.Screens

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import com.example.crudjetpackcompose.Models.Notes
import com.example.crudjetpackcompose.Navigation.NotesNavigationItem
import com.example.crudjetpackcompose.ui.theme.ColorBlack
import com.example.crudjetpackcompose.ui.theme.ColorGrey
import com.example.crudjetpackcompose.ui.theme.ColorLiteGrey
import com.example.crudjetpackcompose.ui.theme.ColorRed
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NotesScreen(navHostController: NavHostController) {

    val db = FirebaseFirestore.getInstance()
    val notesDbRef = db.collection("notes")
    val notesList = remember { mutableStateListOf<Notes>() }
    val dataValue = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        notesDbRef.addSnapshotListener { value, error ->
            if (error == null) {
                val data = value?.toObjects(Notes::class.java)
                notesList.clear()
                notesList.addAll(data!!)
                dataValue.value = true
            } else {
                dataValue.value = false
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                contentColor = White,
                containerColor = ColorRed,
                shape = RoundedCornerShape(100.dp), onClick = {

                    navHostController.navigate(NotesNavigationItem.InsertNotesScreen.route)



                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(ColorBlack)
        )
        {

            Column(
                modifier = Modifier.padding(15.dp)
            ) {
                Text(
                    text = "Create Notes\nCrud",
                    style = TextStyle(
                        fontSize = 32.sp,
                        color = White,
                        fontWeight = FontWeight.Bold
                    )
                )

                if (dataValue.value) {
                    LazyColumn {
                        items(notesList) { notes ->
                            ListItems(notes, notesDbRef)

                        }

                    }

                } else {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(25.dp)
                                .align(Alignment.Center)
                        )
                    }

                }

                LazyColumn {
                    items(notesList) { notes ->
                        ListItems(notes, notesDbRef)

                    }
                }

            }
        }
    }

}

@Composable
fun ListItems(notes: Notes, notesDbRef: CollectionReference) {
    var expanded by remember { mutableStateOf(false) }
    val mContext = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = ColorGrey)

    ) {

        DropdownMenu(modifier = Modifier.background(color = Color.White),
            properties = PopupProperties(clippingEnabled = true),
            offset = DpOffset(x = (-40).dp, y = 0.dp),
            expanded = expanded, onDismissRequest = {
                expanded = false
            }) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Update",
                        style = TextStyle(ColorGrey)
                    )
                },
                onClick = {

                })
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Delete",
                        style = TextStyle(ColorGrey)
                    )
                },
                onClick = {
                    val alertDialog = AlertDialog.Builder(mContext)
                    alertDialog.setMessage("Are you sure to delete this note?")
                    alertDialog.setPositiveButton(
                        "Yes",
                        DialogInterface.OnClickListener { dialog, which ->
                            notesDbRef.document(notes.id).delete()
                            dialog?.dismiss()
                        })
                    alertDialog.setNegativeButton(
                        "No",
                        DialogInterface.OnClickListener { dialog, which ->
                            dialog?.dismiss()
                        })

                })
        }

        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = null,
            tint = White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)
                .clickable {
                    expanded = true
                }
        )
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = notes.tittle, style = TextStyle(color = White))
            Text(text = notes.description, style = TextStyle(color = ColorLiteGrey))
        }
    }
}