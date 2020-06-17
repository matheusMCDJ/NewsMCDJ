package br.iesb.newsmcdj.repository.dto

data class BotRequestDTO(
    val text: String? = null,
    val email: String? = null,
    val sessionId: String? = null
)

data class BotResponseDTO(
    val fulfillmentText: String? = null
)