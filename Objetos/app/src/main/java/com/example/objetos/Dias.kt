package com.example.objetos

enum class Dias (var laboral:Boolean){
    LUNES(false),MARTES(true),MIERCOLES(true),JUEVES(true),VIERNES(true);

    fun saludo(): String{
        when(this){
            LUNES -> return "Hoy es lunes"
            else -> return "Hoy NO es lunes"
        }
    }
}