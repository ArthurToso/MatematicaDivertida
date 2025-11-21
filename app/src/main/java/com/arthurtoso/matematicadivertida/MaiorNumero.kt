package com.arthurtoso.matematicadivertida

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MaiorNumero : AppCompatActivity() {
    lateinit var tvIndice: TextView
    lateinit var tvNumero1: TextView
    lateinit var tvNumero2: TextView
    lateinit var tvNumero3: TextView
    lateinit var editTextNumber: EditText
    lateinit var btnVerificar: Button
    var indice: Int = 0
    var acertos: Int = 0
    var numero1: Int = 0
    var numero2: Int = 0
    var numero3: Int = 0
    lateinit var listaNumeros: MutableList<Int>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_maior_numero)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvIndice = findViewById(R.id.tvIndice)
        tvNumero1 = findViewById(R.id.numero1)
        tvNumero2 = findViewById(R.id.numero2)
        tvNumero3 = findViewById(R.id.numero3)
        btnVerificar = findViewById(R.id.btnVerificar)
        editTextNumber = findViewById(R.id.editTextNumber)

        btnVerificar.setOnClickListener {
            verificarMaiorNumero()
        }
        novaRodada()
    }

    private fun novaRodada() {
        indice = 0
        exibirPerguntaAtual()
    }

    private fun exibirPerguntaAtual(){
        tvIndice.text = "Questão ${indice + 1} de 5"
        numero1 = (0..9).random()
        numero2 = (0..9).random()
        numero3 = (0..9).random()
        tvNumero1.text = numero1.toString()
        tvNumero2.text = numero2.toString()
        tvNumero3.text = numero3.toString()
        listaNumeros = mutableListOf(numero1, numero2, numero3).sortedDescending() as MutableList<Int>
    }

    private fun verificarMaiorNumero(){
        val numeroOrd1 = listaNumeros[0].toString()
        val numeroOrd2 = listaNumeros[1].toString()
        val numeroOrd3 = listaNumeros[2].toString()
        val respostaUsuario = editTextNumber.text.toString()
        val numeroUsuario = respostaUsuario.toIntOrNull()

        val maiorNumeroStr = numeroOrd1 + numeroOrd2 + numeroOrd3
        val maiorNumero = maiorNumeroStr.toInt()

        val alertAcerto: AlertDialog.Builder = AlertDialog.Builder(this)
        val alertErro: AlertDialog.Builder = AlertDialog.Builder(this)

        alertAcerto
            .setTitle("Parabéns!")
            .setMessage("Você Acertou")
            .setPositiveButton("Continuar") {dialog, wich -> dialog.dismiss()}

        alertErro
            .setTitle("Errou!")
            .setMessage("A resposta era: $maiorNumero")
            .setPositiveButton("Continuar") {dialog, wich -> dialog.dismiss()}

        if (numeroUsuario == maiorNumero){
            acertos += 20
            alertAcerto.show()
            proximaPergunta()
        } else {
            alertErro.show()
            proximaPergunta()
        }
    }

    private fun proximaPergunta(){
        indice++
        editTextNumber.text.clear()
        if (indice < 5){
            exibirPerguntaAtual()
        } else {
            finalizarJogo()
        }
    }

    private fun finalizarJogo(){
        //Handler para não fechar instantaneamente a activity,
        //dando um tempo para o usuário ver se acertou ou errou
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MaiorNumeroResultado::class.java)
            intent.putExtra("acertos", acertos)
            startActivity(intent)
            finish()
        }, 1500)
    }

}