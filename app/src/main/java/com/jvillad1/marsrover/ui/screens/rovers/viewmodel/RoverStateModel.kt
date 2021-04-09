package com.jvillad1.marsrover.ui.screens.rovers.viewmodel

import com.jvillad1.marsrover.ui.screens.roverdetails.viewmodel.RoverDetailUI

sealed class RoverState {
    object Loading: RoverState()
    data class Success(val roversList: List<RoverDetailUI>): RoverState()
    object Error: RoverState()
}

sealed class RoverEvent {
    object NavigateToDetails : RoverEvent()
}

sealed class UiAction {
    object LoadRovers : UiAction()
}
