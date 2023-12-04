package com.example.friendfindr.ui.personlist

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.friendfindr.data.model.Person

//kotlin->file
@Composable
fun PersonList(viewModel: PersonListViewModel){

    //instancia del view model
    val people:List<Person> by viewModel.people.observeAsState(listOf())

    viewModel.getAll()

    LazyColumn{
        items(people){person->
            PersonCard(person)
        }
    }
}

@Composable
fun PersonCard(person: Person) {

    Card {
        Row {
            Text(text=person.name.first)
        }
    }

}


