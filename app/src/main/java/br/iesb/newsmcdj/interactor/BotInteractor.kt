package br.iesb.newsmcdj.interactor

import android.content.Context
import br.iesb.newsmcdj.R
import br.iesb.newsmcdj.repository.BotRepository
import br.iesb.newsmcdj.repository.dto.BotRequestDTO

class BotInteractor(private  val context: Context){
    private val botRepository = BotRepository(context, context.getString(R.string.bot_base_url))

    fun topHeadLines(callback: (bot: BotRequestDTO) -> Unit) {
        botRepository.sendMessage(callback)
    }
}