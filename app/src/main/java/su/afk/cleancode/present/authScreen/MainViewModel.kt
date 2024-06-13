package su.afk.cleancode.present.authScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import su.afk.cleancode.domain.repository.AuthRepository
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    var authState by mutableStateOf(AuthState())

    fun authUser(username: String, password: String) {
        viewModelScope.launch {
            repository.login(username = username, password = password)
        }
    }
}