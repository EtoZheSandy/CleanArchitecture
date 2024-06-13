package su.afk.cleancode.data.models

data class AuthBodyRequest(
    val username: String,
    val password: String,
    val expiresInMins: Int
)