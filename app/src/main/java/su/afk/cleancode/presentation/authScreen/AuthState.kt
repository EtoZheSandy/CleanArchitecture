package su.afk.cleancode.presentation.authScreen

// состояние экрана
data class AuthState(
    val isLoading: Boolean = false,
    val signInUsername: String = "emilys",
    val signInPassword: String = "emilyspass",
)