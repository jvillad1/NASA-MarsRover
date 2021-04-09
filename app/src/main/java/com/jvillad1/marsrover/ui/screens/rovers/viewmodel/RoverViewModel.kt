package com.jvillad1.marsrover.ui.screens.rovers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jvillad1.marsrover.data.commons.Output
import com.jvillad1.marsrover.domain.repository.RoversRepository
import com.jvillad1.marsrover.ui.screens.roverdetails.viewmodel.mapToUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoverViewModel @Inject constructor(
    private val roversRepository: RoversRepository
) : ViewModel() {

    private val internalState: MutableStateFlow<RoverState> = MutableStateFlow(RoverState.Loading)
    val uiState: StateFlow<RoverState> = internalState.asStateFlow()

    private val internalEvent = Channel<RoverEvent>(Channel.BUFFERED)
    val uiEvent = internalEvent.receiveAsFlow()

    init {
        onAction(UiAction.LoadRovers)
    }

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.LoadRovers -> getRovers()
        }
    }

    private fun getRovers() = viewModelScope.launch {
        when (val output = roversRepository.getRovers()) {
            is Output.Success -> {
                internalState.value = RoverState.Success(
                    roversList = output.data.map {
                        it.mapToUi()
                    }
                )
            }
            is Output.Error -> {
                internalState.value = RoverState.Error
            }
        }
    }
}
