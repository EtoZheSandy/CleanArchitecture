package su.afk.cleancode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import su.afk.cleancode.present.NavGraphs
import su.afk.cleancode.present.authScreen.AuthScreen
import su.afk.cleancode.ui.theme.CleanCodeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CleanCodeTheme {
                // AuthScreen()
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}