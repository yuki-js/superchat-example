package app.aoki.superchat.example.ui.screens.watchlive

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import app.aoki.superchat.example.ui.theme.SuperchatExampleAppTheme
import com.google.accompanist.insets.*

class WatchLiveActivity : ComponentActivity() {
    val id by lazy { intent.getStringExtra("id") }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {

            SuperchatExampleAppTheme {

                ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        WatchLiveScreen()
                    }
                }
            }
        }
    }
    companion object {
        fun launch(context: Context, id: String) {
            Intent(context, WatchLiveActivity::class.java).apply {
                putExtra("id", id)
            }.also {
                context.startActivity(it)
            }
        }
    }
}
