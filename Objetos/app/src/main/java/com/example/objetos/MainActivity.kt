package com.example.objetos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    object fernanda{
        var apodo:String = "fer";
        fun saludar(): String{
            return "Hola $apodo"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        println(fernanda.saludar())
        fernanda.apodo = "Yagoooo"
        println(fernanda.saludar())

        var sol:Estrella = Estrella("Sol", 2f, "Vía Lactea")

        println(sol)

        var hoy:Dias = Dias.JUEVES

        println(hoy.ordinal)

        println(hoy.laboral)

        hoy.laboral = false;


        println("---------- Funciones de extension ---- ")
        var usuario = "s oooo y yagoooo"

        println("$usuario - ${usuario.noSpaces()}" )

        var array2 = IntArray(3)
        array2[0] = 10
        array2[1] = 20
        array2[2] = 30

        var array3: IntArray = intArrayOf(1,2,3,4,5)

        var lista = mutableListOf<String>();
        lista.add("Hola")

        var mapa = mutableMapOf<String, Int>()
        mapa.put("Yago", 22)

        println(mapa)


        println("La suma de 80  y 20 es: ${calculadora(80,20, :: suma)}")


        var yago = Person("yago", 22, 1.89f)
        if (yago.checkPolicia ( ::inColombia ))
            println("${yago.name} es válido para Colombia")

    }

    private fun String.noSpaces():String{
        return this.replace(" ","");
    }

    private fun calculadora(n1:Int, n2:Int, fn: (Int, Int) -> Int) : Int {
        return fn(n1,n2)
    }

    private fun suma(n1:Int, n2: Int) : Int {return n1+n2}
    private fun resta(n1:Int, n2: Int) : Int {return n1-n2}
    private fun multiplica(n1:Int, n2: Int)  = n1*n2
    private fun divide(n1:Int, n2: Int)  = n1/n2


    private fun inColombia(h:Float):Boolean{
        return h>=1.6f
    }

}
