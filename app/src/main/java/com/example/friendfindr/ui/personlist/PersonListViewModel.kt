package com.example.friendfindr.ui.personlist
import com.example.friendfindr.utils.Result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.friendfindr.data.model.Person
import com.example.friendfindr.repository.PersonRepository

//kotlin->class
class PersonListViewModel (
    private val personRepository: PersonRepository = PersonRepository()
): ViewModel(){//manejar los estados que tenfria personlist

    //_CHARACTER TOMARA EL VALOR DE TOD LO QUE SE LLAMA CON EL REPOSITORY

    //tiene la lista de los person
    private var _people= MutableLiveData<List<Person>>()

    //devolver la lista
    val people get()=_people

    fun getAll(){

        personRepository.getAll { result->
            if (result is Result.Success){
                _people.value=result.data!!.people   //llama a lo que esta en el response
            }
        }
    }

}