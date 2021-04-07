package com.jvillad1.marsrover.data.remote

import com.jvillad1.marsrover.data.remote.model.RoversResponse
import retrofit2.Response
import retrofit2.http.GET

interface MarsRoverPhotosApi {

    @GET("rovers")
    suspend fun getRovers(): Response<RoversResponse>
}
