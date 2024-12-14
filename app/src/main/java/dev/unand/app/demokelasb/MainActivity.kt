package dev.unand.app.demokelasb

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil3.compose.rememberAsyncImagePainter
import dev.unand.app.demokelasb.model.Movie
import dev.unand.app.demokelasb.ui.screen.ListUpcomingScreen
import dev.unand.app.demokelasb.ui.theme.DemoKelasBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        askNotificationPermission(this)
        setContent {
            DemoKelasBTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListUpcomingScreen(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        viewModel = MovieViewModel()
                    )
                }
            }
        }
    }
}

private fun askNotificationPermission(context: Context) {
    // This is only necessary for API level >= 33 (TIRAMISU)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) ==
            PackageManager.PERMISSION_GRANTED
        ) {
//                Log.e(TAG, "PERMISSION_GRANTED")
            // FCM SDK (and your app) can post notifications.
        } else {
                Log.e("MainActivity", "NO_PERMISSION")
            // Directly ask for the permission
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    DemoKelasBTheme {
//        Greeting("Android")
//    }
//}