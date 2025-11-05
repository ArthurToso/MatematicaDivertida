package com.arthurtoso.matematicadivertida

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.arthurtoso.matematicadivertida.model.QuestaoContagem

class Contagem : AppCompatActivity() {

    val listaQuestoes = listOf(
        QuestaoContagem(R.drawable.app_matematica_img_abelha,
            "Quantas abelhas tem na imagem?",
            2,
            listOf(4, 7)
        ),
        QuestaoContagem(R.drawable.app_matematica_img_baloes,
            "Quantos balões tem na imagem",
            6,
            listOf(3,5)
        ),
        QuestaoContagem(R.drawable.app_matematica_img_barracas,
            "Quantas barracas tem na imagem?",
            3,
            listOf(2, 4)
        ),
        QuestaoContagem(R.drawable.app_matematica_img_borboleta,
            "Quantas borboletas tem na imagem?",
            6,
            listOf(8, 4)
        ),
        QuestaoContagem(R.drawable.app_matematica_img_carros,
            "Quantos carros tem na imagem?",
            5,
            listOf(6, 4)
        ),
        QuestaoContagem(R.drawable.app_matematica_img_galinha,
            "Quantas galinhas tem na imagem?",
            7,
            listOf(6, 1)
        ),
        QuestaoContagem(R.drawable.app_matematica_img_maca,
            "Quantas maçãs tem na imagem?",
            7,
            listOf(5, 2)
        ),
        QuestaoContagem(R.drawable.app_matematica_img_mochilas,
            "Quantas mochilas tem na imagem?",
            5,
            listOf(7, 2)
        ),
        QuestaoContagem(R.drawable.app_matematica_img_tartaruga,
            "Quantas tartarugas tem na imagem?",
            1,
            listOf(2, 3)
        ),
        QuestaoContagem(R.drawable.app_matematica_img_xicaras,
            "Quantas xícaras tem na imagem?",
            6,
            listOf(4, 2)
        )
    )

    lateinit var questoesRodada: List<QuestaoContagem>
    var questaoAtual = 0
    var acertos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contagem)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}