package su.afk.cleancode.present.authScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import su.afk.cleancode.domain.models.AuthResult
import su.afk.cleancode.domain.models.User
import su.afk.cleancode.domain.use_case.AuthUseCases
import su.afk.cleancode.domain.use_case.LoginUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
): ViewModel() {

    var authState by mutableStateOf(AuthState())

    // отслеживаем события покоторые поступают со Screen
    fun onEvent(uiEvent: AuthUiEvent) {
        when(uiEvent) {
            is AuthUiEvent.SignInUsernameChanged -> {
                authState = authState.copy(signInUsername = uiEvent.value)
            }
            is AuthUiEvent.SignInPasswordChanged -> {
                authState = authState.copy(signInPassword = uiEvent.value)
            }
            is AuthUiEvent.SignIn -> {
                authUser()
            }
        }
    }

    // канал для отправки событий обратно в screen
    private val resultChannel = Channel<AuthResult<User>>()
    val authResult = resultChannel.receiveAsFlow() // кастим канал в поток и слушаем их в UI

    // авторизация юзера по login/pass
    private fun authUser() {
        viewModelScope.launch {
            authState = authState.copy(isLoading = true)

            val result = authUseCases.loginUseCase(username = authState.signInUsername, password = authState.signInPassword)

            resultChannel.send(result) // отправляем в канал

            authState = authState.copy(isLoading = false)
        }

    }

    init {
        authenticate()
    }

    // если юзер уже был авторизован то проверяем это по токену и автоматически входим в систему
    private fun authenticate() {
        viewModelScope.launch {
            authState = authState.copy(isLoading = true)
            val result = authUseCases.authMeUseCase()
            resultChannel.send(result)
            authState = authState.copy(isLoading = false)
        }
    }
}