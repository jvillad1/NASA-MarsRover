package com.jvillad1.marsrover.data

import com.jvillad1.marsrover.data.commons.Output
import com.jvillad1.marsrover.data.remote.RoversRemoteDataSource
import com.jvillad1.marsrover.domain.RoversRepository
import com.jvillad1.marsrover.ui.screens.rovers.viewmodel.RoverUI
import javax.inject.Inject

class RoversRepositoryImpl @Inject constructor(
    private val roversRemoteDataSource: RoversRemoteDataSource
) : RoversRepository {

    override suspend fun getRovers(): Output<List<RoverUI>> = roversRemoteDataSource.getRovers()
}
