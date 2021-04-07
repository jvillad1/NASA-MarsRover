package com.jvillad1.marsrover.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RoversResponse(
    @Json(name = "rovers") val rovers: List<RoverResponse>?,
)

@JsonClass(generateAdapter = true)
data class RoverResponse(
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "landing_date") val landingDate: String?,
    @Json(name = "launch_date") val launchDate: String?,
    @Json(name = "status") val status: String?,
    @Json(name = "max_sol") val maxSol: Int?,
    @Json(name = "total_photos") val total_photos: Int?,
    @Json(name = "cameras") val cameras: List<CameraResponse>?,
)

@JsonClass(generateAdapter = true)
data class CameraResponse(
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "rover_id") val roverId: Int?,
    @Json(name = "full_name") val fullName: String?,
)
