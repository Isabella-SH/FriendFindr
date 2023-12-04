package com.example.friendfindr.data.remote

import com.example.friendfindr.data.model.PersonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//kotlin->interface
interface PersonService {


    //https://randomuser.me/api/?results=5  ->este es tod el link
    @GET("api/") //colocamos la ultima parte, que es la que llama a todos los resultados
    fun getAll(@Query("results") numResults: Int): Call<PersonResponse>
}