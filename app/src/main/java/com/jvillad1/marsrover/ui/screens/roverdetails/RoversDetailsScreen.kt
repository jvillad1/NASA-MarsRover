package com.jvillad1.marsrover.ui.screens.roverdetails

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jvillad1.marsrover.ui.components.ScreenTitleText
import com.jvillad1.marsrover.ui.screens.roverdetails.viewmodel.RoverDetailUI

@Composable
fun RoverDetailsScreen(
    rover: RoverDetailUI
) {
    ScreenTitleText(title = rover.name)
    Spacer(modifier = Modifier.height(16.dp))


}
