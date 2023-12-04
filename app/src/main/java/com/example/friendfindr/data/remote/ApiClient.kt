package com.example.friendfindr.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//kotlin->object
object ApiClient {

    ///CREO UNA INSTANCIA DEL SERVICIO

    //https://randomuser.me/api/?results=5  ->este es tod el link
    const val FRIEND_FINDR_API_BASE_URL="https://randomuser.me/api/"  //siempre hasta un "/"
    private var personService: PersonService?=null

    fun getPersonService(): PersonService {

        if(personService ==null){

            val retrofit= Retrofit
                .Builder()
                .baseUrl(FRIEND_FINDR_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            personService =retrofit.create(PersonService::class.java)
        }
        return personService as PersonService
    }

}