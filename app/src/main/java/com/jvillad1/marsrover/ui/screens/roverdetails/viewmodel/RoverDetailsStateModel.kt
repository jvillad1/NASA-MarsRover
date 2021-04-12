package com.jvillad1.marsrover.ui.screens.roverdetails.viewmodel

import com.jvillad1.marsrover.ui.model.RoverUI


sealed class RoverState {
    object Loading: RoverState()
    data class Success(val roversList: List<RoverUI>): RoverState()
    object Error: RoverState()
}

sealed class RoverEvent {
    object NavigateToDetails : RoverEvent()
}

sealed class UiAction {
    object LoadRovers : UiAction()
}
