package br.iesb.newsmcdj.interactor

import android.content.Context
import br.iesb.newsmcdj.R
import br.iesb.newsmcdj.domain.News
import br.iesb.newsmcdj.repository.NewsRepository

class NewsInteractor(private val context: Context) {
    private val newsRepository = NewsRepository(context, context.getString(R.string.news_base_url))

    fun topHeadLines(callback: (news: Array<News>) -> Unit) {
        newsRepository.topHeadLines(callback)
    }

}