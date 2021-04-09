package com.jvillad1.marsrover.ui.screens.rovers.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jvillad1.marsrover.ui.screens.rovers.viewmodel.RoverUI

@Composable
fun RoversCardList(
    roverList: List<RoverUI>
) {
    LazyColumn {
        itemsIndexed(items = roverList, itemContent = { index, item ->
            RoverCard(
                modifier = if (index == 0) {
                    Modifier.padding(top = 8.dp)
                } else {
                    Modifier.padding(top = 16.dp)
                },
                rover = item
            )
        })
    }
}
