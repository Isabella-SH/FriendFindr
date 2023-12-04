package com.example.friendfindr

//kotlin->class

import android.app.Application

//es un punto de partida para toda la a`plicacion.
//especificar su nombre en AndroidManifest
//para crear el context de esta aplicacion
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        instance=this
    }

    //objeto que en algun momento se va a inicializar
    //objeto que perdura todos el tiempo
    companion object{
        lateinit var instance: App
    }

}