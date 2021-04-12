package com.jvillad1.marsrover.ui.screens.rovers.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jvillad1.marsrover.ui.components.VerticalGrid
import com.jvillad1.marsrover.ui.model.RoverUI

@Composable
fun RoversCardList(
    roverList: List<RoverUI>,
    onRoverClick: (Int) -> Unit
) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        VerticalGrid(columns = 4) {
            roverList.forEach { item ->
                RoverCard(
                    rover = item,
                    onRoverClick = onRoverClick
                )
            }
        }
    }
}
