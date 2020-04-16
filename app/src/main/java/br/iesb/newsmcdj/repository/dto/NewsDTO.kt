package br.iesb.newsmcdj.repository.dto

import com.google.gson.annotations.SerializedName

data class NewsDTO(

    @SerializedName("urlToImage")
    val image: String? = null,

    val title: String? = null,
    val description: String? = null,

    @SerializedName("publishedAt")
    val releaseDate: String? = null

)

data class NewsListDTO(
    val page: Int? = null,

    val totalResults: Int? = null,

    val pageSize: Int? = null,

    val articles: Array<NewsDTO>? = null
)