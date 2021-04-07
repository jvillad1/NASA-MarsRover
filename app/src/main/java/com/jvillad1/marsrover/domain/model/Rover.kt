package com.jvillad1.marsrover.domain.model

data class Rover(
    val id: Int,
    val name: String,
    val landingDate: String,
    val launchDate: String,
    val status: String,
    val maxSol: Int,
    val total_photos: Int,
    val cameras: List<Camera>,
)

data class Camera(
    val id: Int,
    val name: String,
    val roverId: Int,
    val fullName: String,
)
