package com.example.apibadgebox.ui.kotlinTest

class Persona(name: String, age: Int) {

    private var name:String = name
    private var age:Int = age

    fun SetName(name: String)
    {
        this.name = name
    }

    fun SetAge(age: Int)
    {
        this.age = age
    }
}