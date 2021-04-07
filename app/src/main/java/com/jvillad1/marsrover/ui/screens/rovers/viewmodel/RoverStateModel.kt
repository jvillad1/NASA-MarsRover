package com.jvillad1.marsrover.ui.screens.rovers.viewmodel

data class RoverState(
    val isLoading: Boolean,
    val rovers: List<RoverUI>
) {
    companion object {
        @JvmStatic
        val initialState = RoverState(
            isLoading = false,
            rovers = emptyList()
        )
    }
}

sealed class RoverEvent {
    object RoversError : RoverEvent()
    object NavigateToDetails : RoverEvent()
}

sealed class UiAction {
    object LoadRovers : UiAction()
}
