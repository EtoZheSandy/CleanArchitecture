package su.afk.cleancode.data.repositoty

import android.content.SharedPreferences
import retrofit2.HttpException
import su.afk.cleancode.data.mappers.toUser
import su.afk.cleancode.data.network.service.dto.AuthBodyRequest
import su.afk.cleancode.data.network.service.AuthService
import su.afk.cleancode.domain.repository.AuthRepository
import su.afk.cleancode.domain.model.User
import su.afk.cleancode.common.Resource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthService,
    private val prefs: SharedPreferences
): AuthRepository {

    override suspend fun login(username: String, password: String): Resource<User> {
        val response = try {
            api.loginPost(
                AuthBodyRequest(username, password, 1)
            )
        } catch(e: HttpException) {
            if(e.code() == 401) {
                return Resource.Error(message = "Неверное имя пользователя или пароля")
            }
            // отдает ли сервер название ошибки? если да то запихнем сюда
            return Resource.Error(message = e.message.toString())
        } catch(e: Exception) {
            return Resource.Error(message = e.message.toString())
        }
        // сохраняем токен
        prefs.edit().putString("JWT", response.token).apply()
        return Resource.Success(data = response.toUser())
    }

    override suspend fun authUser(): Resource<User> {
        val token = prefs.getString("JWT", null) ?:
            return Resource.Error(message = "Вы не авторизованы")

        val response = try {
            api.authGet(
                token = token
            )
        } catch(e: Exception) {
            return Resource.Error(message = e.message.toString())
        }

        return Resource.Success(data = response.toUser())
    }

    override suspend fun refreshToken() {

    }
}