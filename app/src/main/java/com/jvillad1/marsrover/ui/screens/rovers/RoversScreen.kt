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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jvillad1.marsrover.R
import com.jvillad1.marsrover.ui.components.ScreenTitleText
import com.jvillad1.marsrover.ui.screens.rovers.model.RoverUI
import com.jvillad1.marsrover.ui.theme.MarsRoverTheme

val rovers = listOf(
    RoverUI("", ""),
    RoverUI("", ""),
    RoverUI("", ""),
    RoverUI("", ""),
    RoverUI("", ""),
)

@Composable
fun RoversScreen() {
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
                val lazyState = rememberLazyListState()
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = lazyState,
                    contentPadding = PaddingValues(start = 32.dp, end = 32.dp, top = 64.dp)
                ) {
                    items(rovers.size) {
                        Row {
                            Column {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Spirit",
                                        style = MaterialTheme.typography.h6,
                                        modifier = Modifier.alignByBaseline()
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "2003-06-10",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    }
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
