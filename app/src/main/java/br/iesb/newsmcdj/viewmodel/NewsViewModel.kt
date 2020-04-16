package br.iesb.newsmcdj.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.iesb.newsmcdj.domain.News
import br.iesb.newsmcdj.interactor.NewsInteractor

// A responsabilidade de uma classe ViewModel é preparar os dados para apresentação (transformações,
// formatação de dados, etc)
//
class NewsViewModel(val app: Application) : AndroidViewModel(app) {
    private val interactor = NewsInteractor(app.applicationContext)

    // Os objetos do Tipo LiveData no Android são tipos que possibilitam o conceito de "Publish/Subscribe",
    // ou seja, as variáveis podem ter "funções ouvintes" que são acionadas sempre que o valor da
    // variável for modificado
    val result = MutableLiveData<Array<News>>()

    fun topHeadLines() {
        interactor.topHeadLines { news ->
            val newsWithImageURL = mutableListOf<News>()

            // Pelo fato da responsabilidade de transformar um dado para apresentação ser das "ViewModels"
            // as linha abaixo modificam os dados antes de serem apresentados para o usuário.
            // Neste caso será acrecentado a URL Base da API The Movie DB para recuperar imagens
            // de um filme criando um novo objeto Movie com essa modificação
            news.forEach { n ->
                val newNews = News(
                    releaseDate = n.releaseDate,
                    image = "https://image.tmdb.org/t/p/w500${n.image}",
                    description = n.description,
                    title =  n.title
                )
                newsWithImageURL.add(newNews)
            }

            result.value = newsWithImageURL.toTypedArray()
        }
    }

}