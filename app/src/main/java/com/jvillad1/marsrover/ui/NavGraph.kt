/*
 * Copyright 2021 shinhyo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jvillad1.marsrover.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.jvillad1.marsrover.ui.screens.roverdetails.RoverDetailsScreen
import com.jvillad1.marsrover.ui.model.CameraUI
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
    NavHost(navController = navController, startDestination = startDestination.route) {
        composable(
            route = NavScreens.MAIN.route
        ) {
            RoversScreen(
                roverViewModel = hiltNavGraphViewModel(),
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
