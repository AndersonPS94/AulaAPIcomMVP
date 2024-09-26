package com.example.aulaapicommvp.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aulaapicommvp.R
import com.example.aulaapicommvp.databinding.ActivityMainBinding
import com.example.aulaapicommvp.model.Postagem
import com.example.aulaapicommvp.model.PostagemAPI
import com.example.aulaapicommvp.presenter.IPostagemPresenter
import com.example.aulaapicommvp.presenter.PostagemPresenter

class MainActivity : AppCompatActivity(),IPostagemPresenter {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var postagemPresenter: PostagemPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val postagemAPI = PostagemAPI()
        postagemPresenter = PostagemPresenter(this, postagemAPI)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    override fun onStart() {
        super.onStart()
        postagemPresenter.recuperarPostagens()
    }

    override fun onDestroy() {
        postagemPresenter.onDestroy()
        super.onDestroy()


    }

    override fun exibirPostagens(lista: List<Postagem>) {
        var textoResultado = ""
        lista.forEach { postagem ->
            textoResultado += "${postagem.id}) - ${postagem.title}\n"
        }
        binding.textResultado.text = textoResultado

    }

    override fun carregando(exibirCarregando: Boolean) {
        if (exibirCarregando) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}