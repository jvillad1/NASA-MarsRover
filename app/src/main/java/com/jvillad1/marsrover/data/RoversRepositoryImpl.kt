package com.jvillad1.marsrover.data

import com.jvillad1.marsrover.data.commons.Output
import com.jvillad1.marsrover.data.remote.RoversRemoteDataSource
import com.jvillad1.marsrover.domain.model.Rover
import com.jvillad1.marsrover.domain.repository.RoversRepository
import javax.inject.Inject

class RoversRepositoryImpl @Inject constructor(
    private val roversRemoteDataSource: RoversRemoteDataSource
) : RoversRepository {

    override suspend fun getRovers(): Output<List<Rover>> = roversRemoteDataSource.getRovers()
}
