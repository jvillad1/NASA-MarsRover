package com.jvillad1.marsrover.ui.screens.rovers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jvillad1.marsrover.data.commons.Output
import com.jvillad1.marsrover.domain.repository.RoversRepository
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

    private val internalState = MutableStateFlow(RoverState.initialState)
    val uiState: StateFlow<RoverState> = internalState.asStateFlow()

    private val internalEvent = Channel<RoverEvent>(Channel.BUFFERED)
    val uiEvent = internalEvent.receiveAsFlow()

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.LoadRovers -> getRovers()
        }
    }

    private fun getRovers() = viewModelScope.launch {
        internalState.value = internalState.value.copy(isLoading = true)

        when (val output = roversRepository.getRovers()) {
            is Output.Success -> {
                internalState.value = internalState.value.copy(
                    rovers = output.data.map {
                        it.mapToUi()
                    }
                )
            }
            is Output.Error -> {
                internalEvent.send(RoverEvent.RoversError)
            }
        }

        internalState.value = internalState.value.copy(isLoading = false)
    }
}
