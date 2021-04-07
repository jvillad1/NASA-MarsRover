package com.jvillad1.marsrover.domain

import com.jvillad1.marsrover.data.commons.Output
import com.jvillad1.marsrover.ui.screens.rovers.viewmodel.RoverUI

interface RoversRepository {

    suspend fun getRovers() : Output<List<RoverUI>>
}
