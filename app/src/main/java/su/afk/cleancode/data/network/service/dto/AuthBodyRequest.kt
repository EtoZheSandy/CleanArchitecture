package su.afk.cleancode.data.network.service.dto

data class AuthBodyRequest(
    val username: String,
    val password: String,
    val expiresInMins: Int
)