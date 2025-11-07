package com.arthurtoso.matematicadivertida

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val btnContagem = findViewById<Button>(R.id.btnContagem)
        val btnAritm = findViewById<Button>(R.id.btnAritm)
        val btnMaiorNum = findViewById<Button>(R.id.btnMaiorNum)
        btnContagem.setOnClickListener {
            val intent = Intent(this, Contagem::class.java)
            startActivity(intent)
        }
        btnAritm.setOnClickListener {
            val intent = Intent(this, AritmeticaBasica::class.java)
            startActivity(intent)
        }
        btnMaiorNum.setOnClickListener {
            val intent = Intent(this, MaiorNumero::class.java)
            startActivity(intent)
        }
    }
}