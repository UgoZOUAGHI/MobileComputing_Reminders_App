package com.example.mobilecomputing.ui.Profile

import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.insets.systemBarsPadding

@Composable
fun Profile(
    sharedPreferences: SharedPreferences,
    onBackPress: () -> Unit,
    navController: NavController
) {
    var username = sharedPreferences.getString("", null)
    var password = sharedPreferences.getString(username, "")
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            TopAppBar {
                IconButton(
                    onClick = onBackPress
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
                    Text(text = "Profile",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                )
                {
                    Icon(
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = null,
                        modifier = Modifier.size(150.dp)
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                Text(text = "Username",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                    )
                    if (username != null) {
                        Text(text = username,
                            fontSize = 20.sp
                        )
                    }

                IconButton(onClick = { navController.navigate("editprofile") }) {
                    Icon(imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp)
                    )
                }
                }
                Row(
                 verticalAlignment = Alignment.CenterVertically
                ){
                Text(text = "Password",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                    if (password != null) {
                        Text(text = password,
                            fontSize = 20.sp
                        )
                    }

                IconButton(onClick = { navController.navigate("editprofile") }) {
                    Icon(imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp)
                    )
                }}
                Spacer(modifier = Modifier.height(250.dp))
                Button(
                    onClick = { navController.navigate(route ="login") },
                    modifier = Modifier
                        .width(118.dp)
                        .size(54.dp)
                ) {
                    Text(text = "Log out",
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray,
                        fontSize = 22.sp
                    )
                }
            }
        }
    }