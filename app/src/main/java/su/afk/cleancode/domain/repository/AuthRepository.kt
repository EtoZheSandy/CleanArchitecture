package su.afk.cleancode.domain.repository

import su.afk.cleancode.domain.model.User
import su.afk.cleancode.common.Resource

interface AuthRepository {

    // Login user and get token
    suspend fun login(username: String, password: String): Resource<User>

    // Get current auth user
    suspend fun authUser(): Resource<User>

    // Refresh auth session
    suspend fun refreshToken()
}