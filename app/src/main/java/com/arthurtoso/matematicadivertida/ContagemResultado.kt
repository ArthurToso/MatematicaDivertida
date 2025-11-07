package com.arthurtoso.matematicadivertida

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContagemResultado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contagem_resultado)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)
        val tvPontuacao = findViewById<TextView>(R.id.tvPontuacao)

        val acertos = intent.getIntExtra("acertos", 0)
        val strAcertos = "$acertos%"
        tvPontuacao.text = strAcertos

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}