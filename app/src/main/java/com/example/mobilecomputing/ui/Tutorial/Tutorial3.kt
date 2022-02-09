package com.example.mobilecomputing.ui.Tutorial

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobilecomputing.R

@Composable
fun Tutorial3(
    onBackPress: () -> Unit,
    navController: NavController
) {
    Scaffold(
        modifier = Modifier.padding(bottom = 24.dp, end = 20.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = onBackPress,
                contentColor = Color.LightGray,
                modifier = Modifier.padding(start = 20.dp),
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
            FloatingActionButton(
                onClick = { navController.navigate(route = "login") },
                contentColor = Color.LightGray,
                modifier = Modifier
                    .padding(start = 80.dp),
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null
                )
            }
        }
    ){

    }
    Row(
    ) {

        Image(painter = painterResource(id = R.drawable.page3),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, bottom = 100.dp)
        )
    }
}