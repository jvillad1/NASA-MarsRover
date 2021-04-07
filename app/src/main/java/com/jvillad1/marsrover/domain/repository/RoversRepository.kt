package com.jvillad1.marsrover.domain.repository

import com.jvillad1.marsrover.data.commons.Output
import com.jvillad1.marsrover.domain.model.Rover

interface RoversRepository {

    suspend fun getRovers() : Output<List<Rover>>
}
