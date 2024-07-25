package com.diberardino.jbomb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diberardino.jbomb.ui.navigation.NavRoutes
import com.diberardino.jbomb.ui.screens.MatchScreen
import com.diberardino.jbomb.ui.theme.JBombTheme

class JBombActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        JBombApplication.activity = this

        // Enable fullscreen immersive mode
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.decorView.systemUiVisibility = (
                android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
                        or android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                )

        setContent {
            JBombTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JBombNavHost()
                }
            }
        }
    }
}

@Composable
fun JBombNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavRoutes.MatchScreen.route
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavRoutes.MatchScreen.route) { MatchScreen() }
    }
}
