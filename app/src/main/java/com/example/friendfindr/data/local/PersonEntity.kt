package com.example.friendfindr.data.local
import androidx.room.Entity
import androidx.room.PrimaryKey

//kotlin->data class

// (Entity): Una entidad en Room representa una tabla dentro de la base de datos.
// Cada instancia de la entidad corresponde a una fila en la tabla.
// En términos sencillos, una entidad es como una clase que representa una
// estructura de datos en tu base de datos.

//cuando haya cambio en la estructura de las Entity, en mi celular:
// 1) voy a settings
// 2) busco apps
// 3) entro a "Aplicaciones"
// 4) selecciono la aplicacion que cree en mi proyecto
// 5) entro a almacenamiento
// 6)doy a "borrar datos"


@Entity(tableName="people")
data class PersonEntity(

    //solo quiero guardar el dato del id
    @PrimaryKey val id:String,

    //evitar el tema d ela imagen ya que se hace complicado
    //si no hubiera conexion a internet la imagen puede no mostrarse
    //guardar las imagenes que traemos, de los favortitos, localmente
    //luego seria apuntar, para mostrar la imagen, a una data local
    val image:String,

    val title:String,
    val name:String,
    val gender: String,
    val city: String
)

