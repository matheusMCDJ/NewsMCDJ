package br.iesb.newsmcdj.interactor

import android.content.Context
import br.iesb.newsmcdj.R
import br.iesb.newsmcdj.domain.News
import br.iesb.newsmcdj.repository.NewsRepository

// Classes do tipo Interactor são criadas para conter Regras de Negócio.
//
class NewsInteractor(private val context: Context) {
    private val newsRepository = NewsRepository(context, context.getString(R.string.news_base_url))

//    fun getNewsDetail(id: Int, callback: (news: News) -> Unit) {
//        if (id < 0) {
//            throw Exception(context.getString(R.string.invalid_news_id))
//        }
//
//        newsRepository.newsDetail(id, callback)
//    }

    fun topHeadLines(callback: (movies: Array<News>) -> Unit) {
        newsRepository.topHeadLines(callback)
    }
}