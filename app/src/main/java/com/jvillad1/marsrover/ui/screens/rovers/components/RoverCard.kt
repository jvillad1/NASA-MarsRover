package com.jvillad1.marsrover.ui.screens.rovers.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.jvillad1.marsrover.R
import com.jvillad1.marsrover.ui.screens.roverdetails.viewmodel.RoverDetailUI
import com.jvillad1.marsrover.ui.theme.MarsRoverTheme

@Composable
fun RoverCard(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    rover: RoverDetailUI
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.secondary,
        modifier = modifier.wrapContentHeight(align = Alignment.CenterVertically)
    ) {
        val context = LocalContext.current
        Column(modifier = Modifier.padding(all = 16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    Modifier.clickable(
                        onClick = {
                            navController.currentBackStackEntry?.arguments?.putParcelable(
                                "rover",
                                rover
                            )
                            navController.navigate("RoverDetails")
                        })
                ) {
                    Text(
                        text = rover.name,
                        style = TextStyle(
                            textDecoration = TextDecoration.Underline,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    )
                }

                Row {
                    Icon(
                        modifier = Modifier.padding(end = 4.dp),
                        painter = painterResource(id = R.drawable.ic_baseline_calendar_today),
                        contentDescription = null
                    )
                    Text(
                        text = rover.launchDate,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RoverCardPreview() {
    MarsRoverTheme() {
        RoverCard(
            rememberNavController(),
            rover = RoverDetailUI(
                id = 1,
                name = "Curiosity",
                landingDate = "",
                launchDate = "2021-04-06",
                status = "",
                maxSol = 0,
                total_photos = 0,
                cameras = emptyList(),
            )
        )
    }
}
