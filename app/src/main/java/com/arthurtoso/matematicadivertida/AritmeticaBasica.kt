package com.arthurtoso.matematicadivertida

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_aritmetica_basica)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        gerarNumeros()
        exibirNumeros()
    }
    private fun gerarNumeros(){
        operando1 = Random.nextInt(0,9)
        operando2 = Random.nextInt(0,9)
    }

}