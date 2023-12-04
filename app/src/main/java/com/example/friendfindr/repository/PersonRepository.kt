package com.example.friendfindr.repository

import com.example.friendfindr.utils.Result
import com.example.friendfindr.data.model.PersonResponse
import com.example.friendfindr.data.remote.ApiClient
import com.example.friendfindr.data.remote.PersonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//kotlin->class
class PersonRepository(
    private val personService:PersonService = ApiClient.getPersonService()
)

{
                                         //posibles resultados de consumir el api:
    fun getAll(callback: (Result<PersonResponse>) -> Unit){

        val getAll=personService.getAll()

        //la variable
        getAll.enqueue(object : Callback<PersonResponse> {

            //cuando la respuesta se a satisfatoria
            override fun onResponse(
                call: Call<PersonResponse>,
                response: Response<PersonResponse>
            ) {

                if(response.isSuccessful){

                    callback(Result.Success(data = response.body()!!))
                }

            }

            //cuando la respuesta no sea satisfatoria
            override fun onFailure(call: Call<PersonResponse>, t: Throwable) {

                callback(Result.Error(message=t.localizedMessage!!))
            }
        })
    }

}