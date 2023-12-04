package com.example.friendfindr.ui.personlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState

//kotlin->file
@Composable
fun PersonList(viewModel: PersonListViewModel){

    //instancia del view model
    val people=viewModel.people.observeAsState(listOf())

}