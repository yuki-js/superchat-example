package app.aoki.superchat.example

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import app.aoki.superchat.example.ui.screens.home.HomeScreen
import app.aoki.superchat.example.ui.screens.home.HomeViewModel
import app.aoki.superchat.example.ui.theme.SuperchatExampleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperchatExampleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AppContent(this)
                }
            }
        }
    }
}

@Composable
fun AppContent(context: Context) {
    HomeScreen(HomeViewModel(context))
}
