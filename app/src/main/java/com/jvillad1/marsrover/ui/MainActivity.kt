package com.jvillad1.marsrover.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jvillad1.marsrover.ui.screens.rovers.RoversScreen
import com.jvillad1.marsrover.ui.screens.rovers.viewmodel.RoverViewModel
import com.jvillad1.marsrover.ui.theme.MarsRoverTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarsRoverTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(
    roverViewModel: RoverViewModel = hiltNavGraphViewModel()
) {
    val navController = rememberNavController()
    val roverUiState = roverViewModel.uiState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(all = 16.dp)) {
            NavHost(navController, startDestination = "rovers") {
                composable("rovers") {
                    RoversScreen(roverUiState.value)
                }
            }
        }
    }
}
