package com.example.friendfindr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.friendfindr.ui.home.Home
import com.example.friendfindr.ui.theme.FriendFindrTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FriendFindrTheme {  //                                                esta linea no cambiarla!!!!!

                // A surface container using the 'background' color from the theme
                Home()
            }
        }
    }
}
