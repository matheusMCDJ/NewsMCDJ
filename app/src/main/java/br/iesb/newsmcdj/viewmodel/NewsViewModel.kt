package br.iesb.newsmcdj.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.iesb.newsmcdj.domain.News
import br.iesb.newsmcdj.interactor.NewsInteractor

class NewsViewModel(val app: Application) : AndroidViewModel(app) {
    private val interactor = NewsInteractor(app.applicationContext)


    val result = MutableLiveData<Array<News>>()

    fun topHeadLines() {
        interactor.topHeadLines { news ->
            val newsWithImageURL = mutableListOf<News>()

            news.forEach { n ->
                val newNews = News(
                    releaseDate = n.releaseDate,
                    image = n.image,
                    description = n.description,
                    title =  n.title
                )
                newsWithImageURL.add(newNews)
            }

            result.value = newsWithImageURL.toTypedArray()
        }
    }

}