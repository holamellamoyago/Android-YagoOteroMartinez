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




        var yago = Person("yago", 22, 1.89f)
        if (yago.checkPolicia ( ::inColombia ))
            println("${yago.name} es válido para Colombia")


        // LAMBDAS -------------------------------------------------
        var funcion = {x:Int , y: Int -> x+y}
        println("La suma de 80  y 20 es: ${calculadora(80,20, funcion)}")

        funcion = {x:Int, y:Int -> x-y}
        println("La resta de 80  y 20 es: ${calculadora(80,20, funcion)}")


        println("La resta de 80  y 20 es: ${calculadora(80,20, {x:Int, y:Int -> x+y})}")

        println("La potencia de 2 elevado a 5 es: ${calculadora(2,5
        ) { x, y ->
            var valor = 1
            for (i in 1..y) valor *= x

            valor
        }
        }")

        // Modificar lambdas -----------------
        var array4 = IntArray(10){5}
        println(array4.toString())

        println("---------------")
        var array5 = IntArray(10){it}
        println(show(array5.toTypedArray()))
        println(array5.joinToString())

        println("-------------")
        var array6 = IntArray(10){it*2}
        println(array6.joinToString())

        var array7= IntArray(10) {i -> i*3 }
        println(array7.joinToString())


        var suma = 0;
        recorrerArray(array7){
            suma += it
        }

        println("La suma del array7 es: $suma")

        var tyyype:TypeAlias;

    }

    private fun recorrerArray(array: IntArray, fn: (Int) -> Unit){
        for (i in array)
            fn(i)
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

    private fun <T> show(array: Array<T>):String{
        var s = "Array: ";

        for ((i) in array.withIndex()){
            s += array[i].toString();
        }

        return s;
    }

}
