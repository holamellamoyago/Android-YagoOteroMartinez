package com.example.objetos

class Person(var name:String, var edad:Int, var height:Float) {
    var alive = true;

    fun die() {alive = false}

    fun checkPolicia(fn:(Float) -> Boolean) : Boolean {
        return fn(height)
    }
}