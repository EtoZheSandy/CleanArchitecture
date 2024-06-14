package su.afk.cleancode.domain.models

// можем добавить любые данные любого типа
sealed class AuthResult<T>(val data: T? = null, val messageError: String? = null) {
    class AuthSuccess<T>(data: T): AuthResult<T>(data = data)
    class AuthError<T>(message: String?): AuthResult<T>(messageError = message)
    class AuthErrorToken<T>(data: T): AuthResult<T>(data = data) // можно и еще для разных ошибок расширить
    class AuthLoading<T>: AuthResult<T>()
}
