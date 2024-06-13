package su.afk.cleancode.data.models

data class LoginBodyPost(
    val username: String,
    val password: String,
    val expiresInMins: Int
)