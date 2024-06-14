package su.afk.cleancode.domain.use_case

import su.afk.cleancode.domain.models.AuthResult
import su.afk.cleancode.domain.models.User
import su.afk.cleancode.domain.repository.AuthRepository
import javax.inject.Inject

class AuthMeUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): AuthResult<User> {

        val result = repository.authUser()

        if(result.messageError != null)
            return AuthResult.AuthError(result.messageError)

        return AuthResult.AuthSuccess(result.data!!)
    }
}