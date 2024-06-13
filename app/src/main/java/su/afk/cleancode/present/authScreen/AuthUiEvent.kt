package su.afk.cleancode.present.authScreen

sealed class AuthUiEvent {
    data class SignUpUsernameChanged(val value: String): AuthUiEvent()
    data class SignUpPasswordChanged(val value: String): AuthUiEvent()
    object SignUp: AuthUiEvent()
}