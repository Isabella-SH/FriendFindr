package com.example.friendfindr.ui.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.friendfindr.R
import com.example.friendfindr.data.local.PersonEntity
import com.example.friendfindr.ui.personlist.PersonListViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage


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

    Card(modifier = Modifier.padding(8.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .padding(8.dp)
        ) {

            GlideImage(
                //imageModel = person.picture.thumbnail,
                imageModel = { person.image ?: R.drawable.default_thumbnail },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                ),
                modifier = Modifier
                    .width(92.dp)
                    .clip(CircleShape)
            )

            // Column para la información del usuario
            Column(modifier = Modifier.weight(5f)) {

                Text(text = person.title ?: "Title name no disponible")
                Text(text = person.name ?: "Last name no disponible")
                Text(text = person.gender ?: "id no disponible")
                Text(text = person.city ?: "email electrónico no disponible")
                Text(text = person.id ?: "id no disponible")
            }
        }
    }
}



