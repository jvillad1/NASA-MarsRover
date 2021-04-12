package com.jvillad1.marsrover.ui.model

import android.os.Parcelable
import com.jvillad1.marsrover.domain.model.Camera
import com.jvillad1.marsrover.domain.model.Rover
import kotlinx.parcelize.Parcelize

@Parcelize
data class RoverUI(
    val id: Int,
    val name: String,
    val landingDate: String,
    val launchDate: String,
    val status: String,
    val maxSol: Int,
    val total_photos: Int,
    val cameras: List<CameraUI>,
) : Parcelable

fun Rover.mapToUi() = RoverUI(
    id = id,
    name = name,
    landingDate = landingDate,
    launchDate = launchDate,
    status = status,
    maxSol = maxSol,
    total_photos = total_photos,
    cameras = cameras.map { it.mapToUi() },
)

@Parcelize
data class CameraUI(
    val id: Int,
    val name: String,
    val roverId: Int,
    val fullName: String,
) : Parcelable

fun Camera.mapToUi() = CameraUI(
    id = id,
    name = name,
    roverId = roverId,
    fullName = fullName,
)
