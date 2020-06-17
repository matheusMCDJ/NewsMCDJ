package br.iesb.newsmcdj.domain

data class News(
    val image: String? = null,
    val title: String? = null,
    val description: String? = null,
    val releaseDate: String? = null
)

data class NewsList(
    val page: Int? = null,
    val totalResults: Int? = null,
    val pageSize: Int? = null,
    val articles: Array<News>? = null
)
