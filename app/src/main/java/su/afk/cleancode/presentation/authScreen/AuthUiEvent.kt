package su.afk.cleancode.presentation.authScreen

sealed class AuthUiEvent {
    data class SignInUsernameChanged(val value: String): AuthUiEvent()
    data class SignInPasswordChanged(val value: String): AuthUiEvent()
    object SignIn: AuthUiEvent()
}