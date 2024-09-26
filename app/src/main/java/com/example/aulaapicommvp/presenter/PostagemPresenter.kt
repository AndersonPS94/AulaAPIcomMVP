package com.example.aulaapicommvp.presenter

import android.util.Log
import com.example.aulaapicommvp.api.RetrofitService
import com.example.aulaapicommvp.model.Postagem
import com.example.aulaapicommvp.model.PostagemAPI
import com.example.aulaapicommvp.view.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class PostagemPresenter(
    private val view: IPostagemPresenter,
    private val postagemAPI: PostagemAPI
) {


    private val coroutine = CoroutineScope(Dispatchers.Main)

    fun recuperarPostagens() {
        view.carregando(true)
        coroutine.launch {
            delay(5000)
            val postagens = postagemAPI.recuperarPostagens()
            //Log.i("resultado_api", "Lista: $postagens ")
            withContext(Dispatchers.Main){

                view.exibirPostagens(postagens)
                view.carregando(false)

            }
        }
    }
        fun onDestroy() {
            coroutine.cancel()

        }
    }
