package com.jvillad1.marsrover.ui.screens.rovers

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jvillad1.marsrover.R
import com.jvillad1.marsrover.ui.components.ScreenTitleText
import com.jvillad1.marsrover.ui.screens.rovers.components.RoversCardList
import com.jvillad1.marsrover.ui.screens.rovers.viewmodel.RoverState

@Composable
fun RoversScreen(
    navController: NavHostController,
    roverState: RoverState
) {
    ScreenTitleText(title = stringResource(id = R.string.rovers_title))
    Spacer(modifier = Modifier.height(16.dp))

    Crossfade(targetState = roverState) { uiState ->
        when (uiState) {
            is RoverState.Success -> {
                RoversCardList(
                    navController = navController,
                    roverList = uiState.roversList
                )
            }
            is RoverState.Loading -> {
                // TODO
            }
            is RoverState.Error -> {
                // TODO
            }
        }
    }
}
