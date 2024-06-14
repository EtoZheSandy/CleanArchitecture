package su.afk.cleancode.domain.use_case

import su.afk.cleancode.domain.models.AuthResult
import su.afk.cleancode.domain.models.User
import su.afk.cleancode.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(username: String, password: String): AuthResult<User> {

        if(username.isEmpty() || password.isEmpty()) {
            return AuthResult.AuthError("Имя пользователя или пароль не может быть пустым")
        }

        val result = repository.login(username = username.trim(), password = password.trim())

        if(result.messageError != null)
            return AuthResult.AuthError(result.messageError)

        return AuthResult.AuthSuccess(result.data!!)
    }
}