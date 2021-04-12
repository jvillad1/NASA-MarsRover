package com.jvillad1.marsrover.ui.screens.rovers

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jvillad1.marsrover.R
import com.jvillad1.marsrover.ui.components.ScreenTitleText
import com.jvillad1.marsrover.ui.screens.rovers.components.RoversCardList
import com.jvillad1.marsrover.ui.screens.rovers.viewmodel.RoverState
import com.jvillad1.marsrover.ui.screens.rovers.viewmodel.RoverViewModel
import timber.log.Timber

@ExperimentalAnimationApi
@Composable
fun RoversScreen(
    roverViewModel: RoverViewModel,
    onRoverClick: (Int) -> Unit
) {
    val roverState = roverViewModel.uiState.collectAsState().value
    val animationState = remember { mutableStateOf(true) }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        AnimatedVisibility(
            visible = !animationState.value,
            enter = fadeIn()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                ScreenTitleText(
                    title = stringResource(id = R.string.rovers_title),
                    style = MaterialTheme.typography.h5
                )

                Spacer(modifier = Modifier.height(16.dp))

                Crossfade(targetState = roverState) { uiState ->
                    when (uiState) {
                        is RoverState.Success -> {
                            RoversCardList(
                                roverList = uiState.roversList,
                                onRoverClick = onRoverClick
                            )
                        }
                        is RoverState.Loading -> {
                            // TODO
                        }
                        is RoverState.Error -> {
                            // TODO
                        }
                    }
                }
            }
        }

        MarsAnimation(
            isFirstLaunch = animationState.value,
            maxWidth = maxWidth,
            maxHeight = maxHeight
        ) {
            animationState.value = false
        }
    }
}

@Composable
fun MarsAnimation(
    isFirstLaunch: Boolean,
    maxWidth: Dp,
    maxHeight: Dp,
    onAnimationFinished: () -> Unit
) {
    Timber.d("Mars")
    val modifier: Modifier

    if (isFirstLaunch) {
        val infiniteTransition = rememberInfiniteTransition()
        val positionState = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1.1f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 2000,
                    easing = LinearOutSlowInEasing
                ),
            )
        )
        modifier = Modifier
            .offset(
                y = (maxHeight) - (maxHeight - maxWidth) * positionState.value,
            )

        if (positionState.value >= 1f) {
            Timber.d("Mars Animation positionState.value = ${positionState.value}")
            onAnimationFinished()
        }
    } else {
        modifier = Modifier
            .offset(
                y = maxHeight - maxWidth,
            )
    }

    Image(
        painter = painterResource(R.drawable.mars_globe),
        modifier = modifier
            .width(maxWidth)
            .height(maxWidth),
        contentDescription = "Mars",
        contentScale = ContentScale.Crop
    )
}
