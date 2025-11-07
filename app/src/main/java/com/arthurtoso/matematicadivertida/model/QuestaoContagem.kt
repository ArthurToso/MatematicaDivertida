package com.arthurtoso.matematicadivertida.model

data class QuestaoContagem (
    val idImagem: Int,
    val pergunta: String,
    val respostaCorreta: Int,
    val respostasErradas: List<Int>
)