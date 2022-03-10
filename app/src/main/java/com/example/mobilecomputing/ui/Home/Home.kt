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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.LatLng

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
                contentColor = LightGray,
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

            val latlng = navController
                .currentBackStackEntry
                ?.savedStateHandle
                ?.getLiveData<LatLng>("location_data")
                ?.value

            HomeAppBar(
                backgroundColor = appBarColor,
                navController = navController
            )
            CategoryReminder(
                viewModel(),
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                latlng = latlng
            )
        }
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
                fontSize = 25.sp,
                color = LightGray,
                modifier = Modifier
                    .padding(start = 5.dp)
                    .heightIn(max = 30.dp) ,

            )
        },
        backgroundColor = backgroundColor,
        actions = {
            IconButton( onClick = {navController.navigate(route = "profile")} ) {
                Icon(imageVector = Icons.Filled.AccountCircle,
                    contentDescription = stringResource(R.string.account),
                modifier = Modifier.size(30.dp)
                )
            }
            IconButton( onClick = { navController.navigate(route = "login") } ) {
                Icon(imageVector = Icons.Filled.Lock,
                    contentDescription = stringResource(R.string.logout),
                    modifier = Modifier.size(25.dp)
                )
            }

        }
    )
}