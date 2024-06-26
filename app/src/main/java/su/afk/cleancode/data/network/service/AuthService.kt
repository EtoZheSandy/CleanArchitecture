package su.afk.cleancode.data.network.service

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import su.afk.cleancode.data.network.service.dto.AuthBodyRequest
import su.afk.cleancode.data.network.service.dto.AuthResponseDto

interface AuthService {

    @POST("auth/login")
    suspend fun loginPost(
        @Body loginBody: AuthBodyRequest
    ): AuthResponseDto

    @GET("auth/me")
    suspend fun authGet(
        @Header("Authorization") token: String
    ): AuthResponseDto
}