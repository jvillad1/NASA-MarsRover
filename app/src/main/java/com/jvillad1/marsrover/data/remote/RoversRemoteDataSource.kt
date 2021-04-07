package com.jvillad1.marsrover.data.remote

import com.jvillad1.marsrover.data.commons.Output
import com.jvillad1.marsrover.ui.screens.rovers.viewmodel.RoverUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoversRemoteDataSource @Inject constructor(
    private val roverPhotosApi: MarsRoverPhotosApi
) {

    suspend fun getRovers(): Output<List<RoverUI>> {
        val response = withContext(Dispatchers.IO) {
            roverPhotosApi.getRovers()
        }
        val body = response.body()

        return if (response.isSuccessful && body != null) {
            val rovers = listOf(
                RoverUI("Curiosity", "2011-11-26"),
                RoverUI("Spirit", "2021-04-05"),
                RoverUI("Opportunity", "2021-04-05"),
                RoverUI("Perseverance", "2021-04-05"),
            )
            Output.success(rovers)
        } else {
            Output.error("error")
        }
    }
}
