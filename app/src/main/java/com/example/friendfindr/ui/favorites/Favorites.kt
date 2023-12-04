package com.example.friendfindr.ui.favorites

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.friendfindr.data.local.PersonEntity
import com.example.friendfindr.ui.personlist.PersonListViewModel


@Composable
fun Favorites(viewModel: PersonListViewModel){

    val favoritePeople=viewModel.getFavoritePeople()

    //si la lista de favoritos no esta vacia
    if(favoritePeople.isNotEmpty()){

        LazyColumn{      //importante importar items!!!!!!!

            items(favoritePeople){person->

                PersonFavoriteCard(person)
            }
        }
    }
    else{
        Text(text = "No hay personas favoritas")
    }
}

@Composable
fun PersonFavoriteCard(person: PersonEntity) {

    Text(text = person.name)
}



