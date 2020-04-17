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

// Interface que define os Endpoints (endereços relativos a uma URL Base) no Retrofit
interface NewsService {

    // Cara Endpoint precisa ser configura de acordo com o método HTTP do servidor
    // No caso da API The Movie DB o endpoint para descoberta de filmes é do tipo GET
    //
    // O Retorno Call do método é um tipo de objeto "Operação" que será executado quando
    // o Método ENQUEUE for acionado no Retrofit
    //
    @GET("top-headlines")
    fun topHeadLines(
        @Query("country") country: String = "br",
        @Query("apikey") apiKey: String = "09086e80be1f42b8a553ec80e7569c39"

    ): Call<NewsListDTO>

}

class NewsRepository(context: Context, baseUrl: String) : BaseRetrofit(context, baseUrl) {
    private val service = retrofit.create(NewsService::class.java)

    fun topHeadLines(callback: (news: Array<News>) -> Unit) {

        // No Retrofit as chamadas são feitas através de um "Service" que contém as definições
        // da requisição HTTP.
        // A conexão inicia quando o método ENQUEUE é chamado
        service.topHeadLines().enqueue(object : Callback<NewsListDTO> {

            // As chamadas do Retrofit são ASSÍNCRONAS e quando terminam a conexão com o
            // servidor chamado executam duas funções:
            // onResponse caso a conexão tenha retornado algum resultado 200 ou equivalente (SUCESSO)
            // onFailure caso a conexão tenha retornado algum erro na chamada 400 ou superior na tabela
            //           de erros HTTP (https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status)
            override fun onResponse(call: Call<NewsListDTO>, response: Response<NewsListDTO>) {
                val result = mutableListOf<News>()
                val news = response.body()?.articles

                // Percorre a lista para converter os objetos DTO (MovieDTO) para Domínio (Movie)
                news?.forEach { n ->

                    // Converte o DTO (Data Transfer Object para um objeto de Domínio de Negócio
                    val domain = News(

                        title = n.title,
                        description = n.description,
                        image = n.image,
                        releaseDate = n.releaseDate
                    )

                    result.add(domain)
                }

                // Chama a função de retorno uma vez que a chamada do Retrofit é ASSÍNCRONA
                callback(result.toTypedArray())
            }

            override fun onFailure(call: Call<NewsListDTO>, error: Throwable) {
                // Caso algum problema ocorra durante a conexão retorna uma lista de filmes vazia
                callback(arrayOf())
            }
        })
    }

}