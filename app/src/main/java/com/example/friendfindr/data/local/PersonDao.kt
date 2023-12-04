package com.example.friendfindr.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

//kotlin->interface

// DAO (Data Access Object): Un DAO en Room es una interfaz o clase abstracta
// que define los métodos para interactuar con la base de datos.
// Los métodos del DAO permiten realizar operaciones como insertar,
// actualizar, eliminar y consultar datos en la base de datos.
@Dao
interface PersonDao {

    @Insert  //funcion de insertar
    fun save(person:PersonEntity)

    //es true cuando el id original sea igual al id que se esta probando
    @Query("select * from people where id=:idparaprobar")
    //debe encontrar un personEntity, sino encuentra, es nulo(?)
    fun getById(idparaprobar:String):PersonEntity?

    @Delete
    fun delete(person: PersonEntity)

    //para llamar a todos mir favoritos, definirla en el repository
    @Query("select * from people")
    fun getAll():List<PersonEntity>
}