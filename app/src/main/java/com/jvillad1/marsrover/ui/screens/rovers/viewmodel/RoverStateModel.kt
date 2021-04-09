package com.jvillad1.marsrover.ui.screens.rovers.viewmodel

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
