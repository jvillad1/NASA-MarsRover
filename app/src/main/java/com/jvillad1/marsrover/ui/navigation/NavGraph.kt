package com.jvillad1.marsrover.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.jvillad1.marsrover.ui.model.CameraUI
import com.jvillad1.marsrover.ui.screens.roverdetails.RoverDetailsScreen
import com.jvillad1.marsrover.ui.screens.rovers.RoversScreen

sealed class NavScreens(val route: String) {
    object MAIN : NavScreens("rovers")
    object DETAIL : NavScreens("rover-details")
    object PHOTOS : NavScreens("camera-photos")
}

@ExperimentalAnimationApi
@Composable
fun NavGraph(startDestination: NavScreens = NavScreens.MAIN) {
    val navController = rememberNavController()
    val actions = remember(navController) { NavigationActions(navController) }
    val launchAnimationState = remember { mutableStateOf(true) }
    NavHost(navController = navController, startDestination = startDestination.route) {
        composable(
            route = NavScreens.MAIN.route
        ) {
            RoversScreen(
                roverViewModel = hiltNavGraphViewModel(),
                launchAnimationState = launchAnimationState,
                onRoverClick = actions.roverDetail
            )
        }
        composable(
            route = "${NavScreens.DETAIL.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            RoverDetailsScreen(
                roverDetailsViewModel = hiltNavGraphViewModel(),
                selectCamera = actions.cameraPhotos
            )
        }
        composable(
            route = "${NavScreens.PHOTOS.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            // TODO: RoverCameraScreen
//            RoverCameraScreen(
//                roverDetailsViewModel = hiltNavGraphViewModel(),
//                selectCamera = actions.cameraPhotos
//            )
        }
    }
}

class NavigationActions(navController: NavHostController) {

    val roverDetail: (Int) -> Unit = { roverId ->
        navController.navigate("${NavScreens.DETAIL.route}/${roverId}")
    }

    val cameraPhotos: (CameraUI) -> Unit = { camera ->
        navController.navigate("${NavScreens.PHOTOS.route}/${camera.id}")
    }
}
