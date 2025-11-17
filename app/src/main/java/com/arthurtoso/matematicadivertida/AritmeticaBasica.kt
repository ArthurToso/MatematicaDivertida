package com.arthurtoso.matematicadivertida

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

var questaoAtual = 0
var acertos = 0
class AritmeticaBasica : AppCompatActivity() {
    private var operando1: Int = 0
    private var operando2: Int = 0
    private var operador: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_aritmetica_basica)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        gerarExpressao()
        exibirNumeros()
    }
    private fun gerarNumeros(){
        operando1 = Random.nextInt(0,9)
        operando2 = Random.nextInt(0,9)
    }
    private fun gerarExpressao(){
        gerarNumeros()
        operador = if (Random.nextBoolean()) "+" else "-"
        val resultado = caclcularExpressao()
        var respostaCerts = if (
            resultado = resposta
        ) else (

        )
    }
    private fun caclcularExpressao(): Int{
        return when (operador){
            "+" -> operando1 + operando2
            "-" -> operando1 - operando2
            else -> 0
        }
    }

    private fun exibirNumeros(){
        findViewById<TextView>(R.id.operando1).text = "$operando1"
        findViewById<TextView>(R.id.operando2).text = "$operando2"
        findViewById<TextView>(R.id.operador).text = "$operador"
    }
}