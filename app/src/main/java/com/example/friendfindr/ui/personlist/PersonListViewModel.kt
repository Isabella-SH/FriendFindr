package com.example.friendfindr.ui.personlist
import androidx.compose.runtime.mutableStateListOf
import com.example.friendfindr.utils.Result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.friendfindr.data.local.PersonEntity
import com.example.friendfindr.data.model.Person
import com.example.friendfindr.repository.PersonRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

//kotlin->class
class PersonListViewModel (
    private val personRepository: PersonRepository = PersonRepository()
): ViewModel(){//manejar los estados que tenfria personlist

    //tiene la lista de los person
    //private var _people= MutableLiveData<List<Person>>()

    // Cambié de MutableLiveData<List<Person>> a MutableStateList<Person>
    private var _people = mutableStateListOf<Person>()
    val people: List<Person> get() = _people //devuelve toda la lista

    // Mutex para garantizar un acceso seguro desde diferentes hilos
    private val mutex = Mutex()

    //devuelve todos las person, metodo del repository que toma del service
    // Cambié la firma de la función para recibir la cantidad de resultados como parámetro

    fun getAll(numResults: Int) {

        viewModelScope.launch {

            // Uso del mutex para un acceso seguro a la lista
            mutex.withLock {

                // Limpio la lista antes de agregar nuevos resultados
                _people.clear()

                // Llamo al método actualizado del repositorio con la cantidad de resultados

                personRepository.getAll(numResults) { result ->
                    if (result is Result.Success) {
                        // Agrego los nuevos resultados a la lista
                        _people.addAll(result.data!!)
                    }
                }
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

