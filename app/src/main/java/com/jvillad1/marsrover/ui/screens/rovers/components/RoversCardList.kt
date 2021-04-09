package com.jvillad1.marsrover.ui.screens.rovers.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jvillad1.marsrover.ui.screens.roverdetails.viewmodel.RoverDetailUI

@Composable
fun RoversCardList(
    navController: NavHostController,
    roverList: List<RoverDetailUI>
) {
    LazyColumn {
        itemsIndexed(items = roverList, itemContent = { index, item ->
            RoverCard(
                navController = navController,
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
