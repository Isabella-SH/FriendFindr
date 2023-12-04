package com.example.friendfindr.repository

import com.example.friendfindr.App
import com.example.friendfindr.data.local.AppDataBase
import com.example.friendfindr.data.local.PersonDao
import com.example.friendfindr.data.local.PersonEntity
import com.example.friendfindr.data.model.Person
import com.example.friendfindr.utils.Result
import com.example.friendfindr.data.model.PersonResponse
import com.example.friendfindr.data.remote.ApiClient
import com.example.friendfindr.data.remote.PersonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//kotlin->class
class PersonRepository(
    private val personService:PersonService = ApiClient.getPersonService(),
    private val personDao: PersonDao= AppDataBase.getInstance(App.instance).personDao(), //al llamarla desde view model, inicializa a characterdao

)

{
                                         //posibles resultados de consumir el api:
    fun getAll(numResults: Int,callback: (Result<List<Person>>) -> Unit){

        val getAll=personService.getAll(numResults)

        //la variable
        getAll.enqueue(object : Callback<PersonResponse> {

            //cuando la respuesta se a satisfatoria
            override fun onResponse(
                call: Call<PersonResponse>,
                response: Response<PersonResponse>
            ) {

                if(response.isSuccessful){

                    //en esta variable guardo todos los person que se obtuvo del response
                    val people=response.body()!!.people

                    //de toda esta lista, busco en cada uno
                    people.forEach{person->
                        //si este character su atributo de isFavorite es true, creamos un CharacterEntity que almacene este character
                        //de esta manera se va agregando characters a la base de datos local
                        person.isFavorite=personDao.getById(person.id.value) !=null
                    }
                    callback(Result.Success(data=response.body()!!.people))
                }

            }

            //cuando la respuesta no sea satisfatoria
            override fun onFailure(call: Call<PersonResponse>, t: Throwable) {

                callback(Result.Error(message=t.localizedMessage!!))
            }
        })
    }


                                          //funciones del Dao:

    //elimina un favorito
    fun delete(person: Person){
        //en el Dao elimino un PersonEntity con la informacion de un person:Person
        //colocar datos en orden de como lo recibe PersonEntity
        personDao.delete(PersonEntity(
            person.id.value,
            person.picture.thumbnail,
            person.name.title,
            person.name.first,
            person.gender,
            person.location.city)
        )
        //luego indica que el atributo de isFavorite ahora es false
        person.isFavorite=false
    }

    //guarda un favorito
    fun save(person: Person){
        //en el Dao guardo un PersonEntity con la informacion de un person:Person
        personDao.save(PersonEntity(
            person.id.value,
            person.picture.thumbnail,
            person.name.title,
            person.name.first,
            person.gender,
            person.location.city)
        )
        //luego indica que el atributo de isFavorite ahora es true
        person.isFavorite=true
    }


    //devuelve todos los favoritos
    fun getAllFavorites():List<PersonEntity>{
        //en el Dao llamo al metodo que devuelve todos los favoritos al realizar el query
        return personDao.getAll()
    }

}

