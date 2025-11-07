package com.arthurtoso.matematicadivertida

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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
            "Quantos balões tem na imagem?",
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

    lateinit var btnOpcao1: Button
    lateinit var btnOpcao2: Button
    lateinit var btnOpcao3: Button
    lateinit var tvIndice: TextView
    lateinit var tvQuestao: TextView
    lateinit var ivQuestao: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contagem)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ivQuestao = findViewById(R.id.ivQuestao)
        tvQuestao = findViewById(R.id.tvQuestao)
        tvIndice = findViewById(R.id.tvIndice)
        btnOpcao1 = findViewById(R.id.botaoOpcao1)
        btnOpcao2 = findViewById(R.id.botaoOpcao2)
        btnOpcao3 = findViewById(R.id.botaoOpcao3)

        btnOpcao1.setOnClickListener { verificarResposta(it) }
        btnOpcao2.setOnClickListener { verificarResposta(it) }
        btnOpcao3.setOnClickListener { verificarResposta(it) }

        novaRodada()
    }

    private fun novaRodada(){
        questoesRodada = listaQuestoes.shuffled().take(5)
        questaoAtual = 0
        exibirPerguntaAtual()
    }

    private fun exibirPerguntaAtual(){
        val questao = questoesRodada[questaoAtual]
        tvIndice.text = "Questão ${questaoAtual + 1} de ${questoesRodada.size}"
        ivQuestao.setImageResource(questao.idImagem)
        tvQuestao.text = questao.pergunta
        val listaOpcoes = mutableListOf<Int>()
        listaOpcoes.add(questao.respostaCorreta)
        listaOpcoes.add(questao.respostasErradas[0])
        listaOpcoes.add(questao.respostasErradas[1])
        listaOpcoes.shuffle()
        btnOpcao1.text = listaOpcoes[0].toString()
        btnOpcao1.tag = listaOpcoes[0]
        btnOpcao2.text = listaOpcoes[1].toString()
        btnOpcao2.tag = listaOpcoes[1]
        btnOpcao3.text = listaOpcoes[2].toString()
        btnOpcao3.tag = listaOpcoes[2]
    }

    private fun verificarResposta(botaoClicado: View){
        val respostaUsuario = botaoClicado.tag as Int
        val respostaCorreta = questoesRodada[questaoAtual].respostaCorreta

        val alertAcerto: AlertDialog.Builder = AlertDialog.Builder(this)
        val alertErro: AlertDialog.Builder = AlertDialog.Builder(this)

        alertAcerto
            .setTitle("Parabéns!")
            .setMessage("Você Acertou")
            .setPositiveButton("Continuar") {dialog, wich -> dialog.dismiss()}

        alertErro
            .setTitle("Errou!")
            .setMessage("A resposta era: $respostaCorreta")
            .setPositiveButton("Continuar") {dialog, wich -> dialog.dismiss()}

        if (respostaUsuario == respostaCorreta){
            //Acerto
            acertos += 20
            alertAcerto.show()
            proximaPergunta()
        } else {
            //Erro
            alertErro.show()
            proximaPergunta()
        }
    }

    private fun proximaPergunta(){
        questaoAtual ++
        if (questaoAtual < questoesRodada.size){
            exibirPerguntaAtual()
        } else {
            finalizarJogo()
        }
    }

    private fun finalizarJogo(){
        //Handler para não fechar instantaneamente a activity,
        //dando um tempo para o usuário ver se acertou ou errou
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, ContagemResultado::class.java)
            intent.putExtra("acertos", acertos)
            startActivity(intent)
            finish()
        }, 1500)
    }
}