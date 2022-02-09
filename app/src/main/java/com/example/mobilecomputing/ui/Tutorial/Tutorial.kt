package com.example.mobilecomputing.ui.Tutorial

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobilecomputing.R


@Composable
fun Tutorial(
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
                onClick = { navController.navigate(route = "tutorial2") },
                contentColor = Color.LightGray,
                modifier = Modifier
                    .padding(start = 80.dp),
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null
                )
            }
        }
    ){

    }
    Column(modifier = Modifier.fillMaxSize()
        .padding(start = 16.dp, top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    )
    {
        Text(text = "Welcome to the Tutorial !",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = Color.LightGray,
        )
    }

    Row(
    ) {

        Image(painter = painterResource(id = R.drawable.page1),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 39.dp, bottom = 110.dp)
        )
    }

}
