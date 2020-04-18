package br.iesb.newsmcdj.repository

import android.content.Context
import br.iesb.newsmcdj.domain.News
import br.iesb.newsmcdj.repository.dto.NewsDTO
import br.iesb.newsmcdj.repository.dto.NewsListDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    fun topHeadLines(
        @Query("country") country: String = "br",
        @Query("apikey") apiKey: String = "09086e80be1f42b8a553ec80e7569c39"

    ): Call<NewsListDTO>

}

class NewsRepository(context: Context, baseUrl: String) : BaseRetrofit(context, baseUrl) {
    private val service = retrofit.create(NewsService::class.java)

    fun topHeadLines(callback: (news: Array<News>) -> Unit) {

        service.topHeadLines().enqueue(object : Callback<NewsListDTO> {

            override fun onResponse(call: Call<NewsListDTO>, response: Response<NewsListDTO>) {
                val result = mutableListOf<News>()
                val news = response.body()?.articles

                news?.forEach { n ->

                    val domain = News(

                        title = n.title,
                        description = n.description,
                        image = n.image,
                        releaseDate = n.releaseDate
                    )

                    result.add(domain)
                }

                callback(result.toTypedArray())
            }

            override fun onFailure(call: Call<NewsListDTO>, error: Throwable) {
                callback(arrayOf())
            }
        })
    }

}