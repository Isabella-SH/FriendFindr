package com.example.friendfindr.ui.personlist

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.friendfindr.R
import com.example.friendfindr.data.model.Person
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

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

    //creo un estado que guarde si el character es un favorito o no
    val isFavorite= remember{ mutableStateOf(false) }


    Card(modifier = Modifier.padding(8.dp)) {
        Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(8.dp)
        ) {

            GlideImage(
                //imageModel = person.picture.thumbnail,
                imageModel = { person.picture.thumbnail ?: R.drawable.default_thumbnail },
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

                Text(text = person.name.first ?: "First name no disponible")
                Text(text = person.name.last ?: "Last name no disponible")
                Text(text = person.id.value ?: "id no disponible")
                Text(text = person.email ?: "email electrónico no disponible")
                Text(text = person.cell ?: "cell no disponible")
            }

            // Spacer para separar la información del usuario del IconButton
            Spacer(modifier = Modifier.width(8.dp))

            // IconButton para el favorito
            IconButton(
                onClick = {
                    // Lógica de insertar o eliminar favorito
                    if (isFavorite.value) {
                        //delete()
                    } else {
                        //insert()
                    }

                    //cada que de click, el valor que tenia, ahora sera lo contrario
                    isFavorite.value = !isFavorite.value
                },
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)  // Alinea el IconButton verticalmente en el centro
            ) {
                Icon(
                    Icons.Filled.Favorite,
                    null,
                    //si esta marcado como favorito cambia a un color "primary", sino sera un color "onSurface"
                    tint = if (isFavorite.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }

}

