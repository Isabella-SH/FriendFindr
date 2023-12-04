package com.example.friendfindr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.friendfindr.ui.personlist.PersonList
import com.example.friendfindr.ui.personlist.PersonListViewModel
import com.example.friendfindr.ui.theme.FriendFindrTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel:PersonListViewModel by viewModels()
        setContent {
            FriendFindrTheme {  //                                                esta linea no cambiarla!!!!!

                // A surface container using the 'background' color from the theme

                PersonList(viewModel)
            }
        }
    }
}
