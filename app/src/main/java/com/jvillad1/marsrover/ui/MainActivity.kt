package com.jvillad1.marsrover.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.google.accompanist.insets.ProvideWindowInsets
import com.jvillad1.marsrover.ui.theme.MarsRoverTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarsRoverApp {
                ProvideWindowInsets {
                    NavGraph()
                }
            }
        }
    }
}

@Composable
fun MarsRoverApp(content: @Composable () -> Unit) {
    MarsRoverTheme {
        content()
    }
}
