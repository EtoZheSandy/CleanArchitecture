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
import su.afk.cleancode.domain.repository.AuthRepository
import su.afk.cleancode.domain.repository.models.User
import su.afk.cleancode.util.Resource
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    fun authUser(username: String, password: String) {
        viewModelScope.launch {
            repository.login(username = username, password = password)
        }
    }


    var authState by mutableStateOf(AuthState())

    private val resultChannel = Channel<Resource<User>>()
    val authResults = resultChannel.receiveAsFlow()

    init {
        authenticate()
    }

    fun onEvent(event: AuthUiEvent) {
        when(event) {
            is AuthUiEvent.SignUpUsernameChanged -> {
                authState = authState.copy(signUpUsername = event.value)
            }
            is AuthUiEvent.SignUpPasswordChanged -> {
                authState = authState.copy(signUpPassword = event.value)
            }
            is AuthUiEvent.SignUp -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            authState = authState.copy(isLoading = true)
            val result = repository.login(
                username = authState.signUpUsername,
                password = authState.signUpPassword
            )
            resultChannel.send(result)
            authState = authState.copy(isLoading = false)
        }
    }

    private fun authenticate() {
        viewModelScope.launch {
            authState = authState.copy(isLoading = true)
            val result = repository.authUser()
            resultChannel.send(result)
            authState = authState.copy(isLoading = false)
        }
    }
}