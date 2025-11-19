package com.arthurtoso.matematicadivertida

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random


class AritmeticaBasica : AppCompatActivity() {
    var questaoAtual = 0
    var acertos = 0
    private var operando1: Int = 0
    private var operando2: Int = 0
    private var operador: String = ""
    private lateinit var resposta: EditText
    private var resultadoCorreto: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_aritmetica_basica)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        resposta = findViewById(R.id.editTextNumberDecimal)
        btnVerificar()
        novaQuestao()
        atualizarQuestao()
    }
    private fun gerarNumeros(){
        operando1 = Random.nextInt(0,10)
        operando2 = Random.nextInt(0,10)
    }
    private fun gerarExpressao(){
        gerarNumeros()
        operador = if (Random.nextBoolean()) "+" else "-"
        if (operando2 > operando1 && operador == "-"){
            val temp = operando1
            operando1 = operando2
            operando2 = temp
        }
        resultadoCorreto = caclcularExpressao()
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
        findViewById<TextView>(R.id.operador).text = operador
    }

    private fun btnVerificar(){
        findViewById<Button>(R.id.verificar).setOnClickListener {
            verificarResposta()
        }
    }

    private fun verificarResposta(){
        val campoResposta = resposta.text.toString().toIntOrNull()
        val acertou = campoResposta == resultadoCorreto

        if(acertou){
            acertos++
        }
        mostrarResultado(acertou, resultadoCorreto)
    }
    private fun mostrarResultado(acertou: Boolean, resultadoCorreto: Int) {
        val titulo = if (acertou) "🎉 Correto!" else "❌ Incorreto"
        val mensagem = if (acertou) {
            "Parabéns! Você acertou!"
        } else {
            "A resposta correta era: $resultadoCorreto"
        }
        val alertDialog = AlertDialog.Builder(this)
            .setTitle(titulo)
            .setMessage(mensagem)
            .setPositiveButton("OK") { dialog, _ ->
                if (questaoAtual < 4) {
                    questaoAtual++
                    atualizarQuestao()
                    novaQuestao()
                } else {
                    mostrarResultadoFinal()
                }
                dialog.dismiss()
            }
            .setCancelable(false)
            .create()

        alertDialog.show()
    }
    private fun mostrarResultadoFinal() {
        val nota = acertos
        val mensagem = when (nota) {
            5 -> "Excelente! 🎉\nVocê acertou todas as 5 questões!"
            4 -> "Muito bom! 👍\nVocê acertou 4 de 5 questões!"
            3 -> "Bom! 😊\nVocê acertou 3 de 5 questões!"
            2 -> "Pode melhorar! 🤔\nVocê acertou 2 de 5 questões."
            else -> "Estude mais! 📚\nVocê acertou $nota de 5 questões."
        }
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Fim do Quiz!")
            .setMessage("Sua nota: $nota/5\n\n$mensagem")
            .setPositiveButton("Jogar Novamente") { dialog, _ ->
                reiniciarQuiz()
                dialog.dismiss()
            }
            .setNegativeButton("Sair") { dialog, _ ->
                finish()
            }
            .setCancelable(false)
            .create()
        alertDialog.show()
    }
    private fun reiniciarQuiz() {
        questaoAtual = 0
        acertos = 0
        novaQuestao()
        atualizarQuestao()
    }
    private fun novaQuestao() {
        gerarExpressao()
        exibirNumeros()
        resposta.text.clear()
        resposta.hint = "Digite a resposta"
        resposta.requestFocus()
    }
    private fun atualizarQuestao() {
        findViewById<TextView>(R.id.tvIndice).text = "Questão ${questaoAtual + 1}"
    }
}