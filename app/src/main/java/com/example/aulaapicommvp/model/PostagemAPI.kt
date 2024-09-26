package com.example.aulaapicommvp.model

import com.example.aulaapicommvp.api.RetrofitService

class PostagemAPI {
    suspend  fun recuperarPostagens(): List<Postagem> {

        val jsonPlace = RetrofitService.recuperarJsonPlace()
        //var retorno: Response<List<Postagem>>? = null

        try {
            val retorno = jsonPlace.recuperarPostagens()

            if (retorno.isSuccessful) {
                retorno.body()?.let { postagens ->

                    return postagens
                }
            }

        } catch (erroRecuperarPostagem: Exception) {
            erroRecuperarPostagem.printStackTrace()

        }
        return emptyList()
    }
}