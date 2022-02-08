package com.example.mobilecomputing.Profile

import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobilecomputing.Home.HomeContent
import com.google.accompanist.insets.systemBarsPadding

@Composable
fun Profile(
    sharedPreferences: SharedPreferences,
    onBackPress: () -> Unit,
    navController: NavController
) {
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
                        fontSize = 20.sp
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
                Text(text = "Username : ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                    )
                    Text(text = "Username to add",
                    fontSize = 20.sp
                    )

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
                Text(text = "Password : ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                    Text(text = "Password to add hide",
                        fontSize = 20.sp
                    )

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
                        .width(88.dp)
                        .size(44.dp)
                ) {
                    Text(text = "Log out",
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray
                    )
                }
            }
        }
    }