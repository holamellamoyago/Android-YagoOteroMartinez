package com.example.objetos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.objetos.Subclase.anidada


// Declaro el alias quí pero no hace falta pasarle el constructor
typealias aliasObjeto = Subclase.anidada

// Otros tipo de datos con alias
typealias  aliasDato = MutableMap<Int, ArrayList<String>>

class TypeAlias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_type_alias)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var lista = mutableListOf<String>();
        lista.add("Hi , bye");


        // Forma tradicional
        var ani = Subclase.anidada("")

        // Forma con alias donde ahora sí se le pasa el constructor
        var anidada = aliasObjeto("")

        // EL mutable map
        var saludos: aliasDato = sortedMapOf()
        saludos.put(key = 3, value = arrayListOf("Hola, chao"))
        saludos.put(key = 1, value = ArrayList(lista))
        println(saludos)

    }


}

