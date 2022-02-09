package com.example.mobilecomputing.ui.Home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobilecomputing.R
import com.google.accompanist.insets.systemBarsPadding
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Home(
    navController: NavController
) {
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeContent(
                navController = navController
            )
        }
}

@Composable
fun HomeContent(
    navController: NavController
) {
    Scaffold(
        modifier = Modifier.padding(bottom = 24.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(route = "reminders") },
                contentColor = Color.LightGray,
                modifier = Modifier.padding(all = 20.dp),
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxWidth()
        ) {
            val appBarColor = MaterialTheme.colors.primary

            HomeAppBar(
                backgroundColor = appBarColor,
                navController = navController
            )

        }
        /*ReminderList(
            viewModel(),
            modifier = Modifier.fillMaxSize(),
            navController = navController
        )*/
    }
}

@Composable
private fun HomeAppBar(
    navController: NavController,
    backgroundColor: Color
) {
    TopAppBar(
        title = {
            Text(
                text = "All Reminders",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = LightGray,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .heightIn(max = 25.dp) ,

            )
        },
        backgroundColor = backgroundColor,
        actions = {
            IconButton( onClick = {navController.navigate(route = "profile")} ) {
                Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = stringResource(R.string.account))
            }
            IconButton( onClick = { navController.navigate(route = "login") } ) {
                Icon(imageVector = Icons.Filled.Lock, contentDescription = stringResource(R.string.logout))
            }

        }
    )
}