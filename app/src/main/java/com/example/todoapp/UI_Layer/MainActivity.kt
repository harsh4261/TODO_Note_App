package com.example.todoapp.UI_Layer

import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.todoapp.Navigation.Nav1
import com.example.todoapp.ViewModel.TodoViewModel
import com.example.todoapp.ui.theme.TODOAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(DelicateCoroutinesApi::class)
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Install the splash screen
        val splashScreen = installSplashScreen()

        // Set the condition to keep the splash screen on the screen longer
        splashScreen.setKeepOnScreenCondition {
            lifecycleScope.launch {
                delay(3000) // Keep splash screen for 4 seconds
                splashScreen.setKeepOnScreenCondition { false } // Dismiss after delay
            }
            true // Initially keep it on screen
        }
        enableEdgeToEdge()
        setContent {
            val viewModel = hiltViewModel<TodoViewModel>()
            TODOAppTheme {
                 Nav1(viewModel)
            }
        }
    }
}

