package com.example.friendfindr.ui.personlist
import com.example.friendfindr.utils.Result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.friendfindr.data.local.PersonEntity
import com.example.friendfindr.data.model.Person
import com.example.friendfindr.repository.PersonRepository

//kotlin->class
class PersonListViewModel (
    private val personRepository: PersonRepository = PersonRepository()
): ViewModel(){//manejar los estados que tenfria personlist

    //tiene la lista de los person
    private var _people= MutableLiveData<List<Person>>()

    //devolver la lista
    val people get()=_people


                            //devuelve todos las person, metodo del repository que toma del service
    fun getAll(numResults: Int) {

        personRepository.getAll(numResults) { result->
            if (result is Result.Success){
                _people.value=result.data!!   //llama a lo que esta en el response
            }
        }
    }


                                     //metodos del repository que toma del Dao

    //borrar de favoritos
    fun delete(person: Person){
        personRepository.delete(person)
    }

    //guardar como favoritos
    fun save(person: Person){
        personRepository.save(person)
    }

    //devuelva la lista de toda la tabla PersonEntity
    fun getFavoritePeople(): List<PersonEntity> {
        return personRepository.getAllFavorites()
    }

}

