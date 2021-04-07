package com.jvillad1.marsrover.ui.screens.rovers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import com.jvillad1.marsrover.R
import com.jvillad1.marsrover.ui.components.ScreenTitleText
import com.jvillad1.marsrover.ui.screens.rovers.viewmodel.RoverViewModel
import com.jvillad1.marsrover.ui.screens.rovers.viewmodel.UiAction
import com.jvillad1.marsrover.ui.theme.MarsRoverTheme

@Composable
fun RoversScreen(
    viewModel: RoverViewModel = hiltNavGraphViewModel()
) {
    val state = viewModel.uiState.collectAsState()
    val rovers = state.value.rovers

    viewModel.onAction(UiAction.LoadRovers)

    Scaffold() {
        Column() {
            Spacer(modifier = Modifier.height(16.dp))
            ScreenTitleText(title = stringResource(R.string.rovers_title))
            Spacer(modifier = Modifier.height(32.dp))
            Surface(
                elevation = 8.dp,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)),
            ) {
                if (rovers != null && rovers.isNotEmpty()) {
                    val lazyState = rememberLazyListState()
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        state = lazyState,
                        contentPadding = PaddingValues(start = 32.dp, end = 32.dp, top = 64.dp)
                    ) {
                        items(rovers) { rover ->
                            Row {
                                Column {
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = rover.name,
                                            style = MaterialTheme.typography.h6,
                                            modifier = Modifier.alignByBaseline()
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = rover.launchDate,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                }
                            }
                        }
                    }
                } else if (rovers != null && rovers.isEmpty()) {
                    Text(
                        text = "No Rovers found",
                        modifier = Modifier.fillMaxSize(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_2
)
@Composable
fun DefaultPreview() {
    MarsRoverTheme(darkTheme = false) {
        RoversScreen()
    }
}
