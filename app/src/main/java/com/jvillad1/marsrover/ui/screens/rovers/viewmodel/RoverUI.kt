package com.jvillad1.marsrover.ui.screens.rovers.viewmodel

import com.jvillad1.marsrover.domain.model.Rover

data class RoverUI(
    val name: String,
    val launchDate: String
)

fun Rover.mapToUi() = RoverUI(
    name = name,
    launchDate = launchDate,
)
