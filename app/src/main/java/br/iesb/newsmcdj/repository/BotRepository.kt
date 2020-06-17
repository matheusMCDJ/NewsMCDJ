package br.iesb.newsmcdj.repository

import android.content.Context
import android.widget.Toast
import br.iesb.newsmcdj.domain.News
import br.iesb.newsmcdj.repository.dto.BotRequestDTO
import br.iesb.newsmcdj.repository.dto.BotResponseDTO
import br.iesb.newsmcdj.repository.dto.NewsDTO
import br.iesb.newsmcdj.repository.dto.NewsListDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*

interface BotService {

    @POST("message/text/send")
    fun sendMessage(
        @Body botRequestDTO: BotRequestDTO
    ): Call<BotResponseDTO>

}

class BotRepository(context: Context, baseUrl: String) : BaseRetrofit(context, baseUrl) {
    private val service = retrofit.create(BotService::class.java)

    fun sendMessage(callback: (bot: BotRequestDTO) -> Unit){

        service.sendMessage(BotRequestDTO()).enqueue(object : Callback<BotResponseDTO> {

            override fun onResponse(call: Call<BotResponseDTO>, response: Response<BotResponseDTO>) {

            }

            override fun onFailure(call: Call<BotResponseDTO>, t: Throwable) {

            }




        })
    }

}