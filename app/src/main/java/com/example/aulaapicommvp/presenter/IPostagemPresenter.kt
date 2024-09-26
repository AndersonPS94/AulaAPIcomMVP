package com.example.aulaapicommvp.presenter

import com.example.aulaapicommvp.model.Postagem

interface IPostagemPresenter {
    fun exibirPostagens(lista: List<Postagem>)
    fun carregando (exibirCarregando: Boolean)

}