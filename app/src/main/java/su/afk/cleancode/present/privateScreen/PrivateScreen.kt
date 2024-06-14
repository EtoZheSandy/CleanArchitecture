package su.afk.cleancode.present.privateScreen

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import su.afk.cleancode.domain.models.User

@Destination
@Composable
fun PrivateScreen(
    user: User
) {
     Column(
        modifier = Modifier.fillMaxSize(),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center
    ) {
         AsyncImage(
             model = user.imageUrl,
             contentDescription = null,
             modifier = Modifier.height(100.dp)
         )
        Text(text = "Добро пожаловать: ${user.lastName}")

         Spacer(modifier = Modifier.height(20.dp))
        Text(text = "email: ${user.email}")
    }
}