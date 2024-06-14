package su.afk.cleancode.present.authScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import su.afk.cleancode.domain.models.AuthResult
import su.afk.cleancode.present.destinations.AuthScreenDestination
import su.afk.cleancode.present.destinations.PrivateScreenDestination

@Composable
@Destination(start = true)
fun AuthScreen(
    navigator: DestinationsNavigator,
    viewModel: MainViewModel = hiltViewModel(),
) {

    val state = viewModel.authState

    val context = LocalContext.current

    // если что то из viewModel или context поменяется то запустится LaunchedEffect
    LaunchedEffect(key1 = viewModel, key2 = context) {
        viewModel.authResult.collect{ authResult ->
            when(authResult) {
                is AuthResult.AuthSuccess -> {
                    navigator.navigate(PrivateScreenDestination(authResult.data!!)) {
                        popUpTo(AuthScreenDestination.route) {
                            inclusive = true
                        }
                    }
                }
                is AuthResult.AuthError -> {
                    Toast.makeText(context, authResult.messageError, Toast.LENGTH_LONG).show()
                }
                is AuthResult.AuthErrorToken -> {
                    Toast.makeText(context, "Время сессии вышло", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Авторизуйтесь",
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = state.signInUsername,
            placeholder = { Text(text = "Логин") },
            onValueChange = {
                viewModel.onEvent(AuthUiEvent.SignInUsernameChanged(it))
            })
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = state.signInPassword,
            placeholder = { Text(text = "Пароль") },
            onValueChange = {
                viewModel.onEvent(AuthUiEvent.SignInPasswordChanged(it))
            }
        )

        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                viewModel.onEvent(AuthUiEvent.SignIn)
            }
        ) {
            Text(text = "Войти")
        }

        Spacer(modifier = Modifier.height(10.dp))
        if(state.isLoading) {
            CircularProgressIndicator(
                color = Color.Black,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}
