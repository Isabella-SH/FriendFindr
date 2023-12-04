package com.example.friendfindr.utils

//kotlin->sealed class

sealed class Result<T>(val data: T?=null, val message:String?=null){

    //si la consulta es exitosa, entonces solo paso el parametro de la data e indico que la data=data(evitar que sea nula)
    class Success<T>(data:T):Result<T>(data=data)

    //si la consulta es error, paso el mensaje no es necesario pasarla data(por si solo es nula)
    class Error<T>(message: String?,data:T?=null):Result<T>(data=data)
}
