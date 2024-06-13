package su.afk.cleancode.domain.repository

interface AuthRepository {

    // Login user and get token
    fun login(username: String, password: String)

    // Get current auth user
    fun authUser()

    // Refresh auth session
    fun refreshToken()
}