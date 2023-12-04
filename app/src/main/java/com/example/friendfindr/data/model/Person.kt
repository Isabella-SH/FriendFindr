package com.example.friendfindr.data.model

import com.google.gson.annotations.SerializedName

//kotlin->data class
//kotlin->data class

//creamos los atributos con el mismo nombre que aparece en el json
//caso contrario agrgar @SerializedName("nombreoriginal")
data class Person(  //datos de cada heroe

    //para la lista general

    /*
• Nombre (name/first)
• Apellido (name/last)
• Email (email)
• Celular (cell)
• Foto (picture/thumbnail)
    * */
    val id:Id,
    val name: Name,
    val email:String,
    val cell:String,
    val picture: Picture,


    //para favoritos

    /*
• Título (name/title)
• Nombre (name/first)
• Género (gender)
• Ciudad (location/city)
• Foto (picture/thumbnail)
*/
    val gender:String,
    val location:Location,

    var isFavorite:Boolean
)
data class Id(
    val value:String,
)

data class Name(
    val title:String,
    val first: String,
    val last:String,
)

data class Picture(
    val thumbnail:String,
)

data class Location(
    val city:String,
)

data class PersonResponse( //lo que devuelve el endpoint, lo capturamos en una variable people
    @SerializedName("results")//nombre original en el json, pero ahora se llamara "people"
    val people:List<Person> //endpoint devuelve una lista de person
)

