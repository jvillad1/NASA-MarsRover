package com.jvillad1.marsrover.data.remote

import com.jvillad1.marsrover.data.commons.Output
import com.jvillad1.marsrover.data.remote.model.mapToDomain
import com.jvillad1.marsrover.domain.model.Rover
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoversRemoteDataSource @Inject constructor(
    private val roverPhotosApi: MarsRoverPhotosApi
) {

    suspend fun getRovers(): Output<List<Rover>> {
        val response = withContext(Dispatchers.IO) {
            roverPhotosApi.getRovers()
        }
        val body = response.body()

        return if (response.isSuccessful && body != null) {
            val rovers = body.rovers?.map {
                it.mapToDomain()
            }.orEmpty()

            Output.success(rovers)
        } else {
            Output.error("Error getting the Rovers list")
        }
    }
}
