package com.example.objetos

data class Estrella(
    val name:String,
    val radius:Float,
    var galaxy:String,
){
    var alive = true;
    override fun toString(): String {
        return "Estrella(name='$name', alive=$alive)"
    }
}
