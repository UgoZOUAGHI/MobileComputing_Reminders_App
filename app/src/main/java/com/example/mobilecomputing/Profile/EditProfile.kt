package com.example.mobilecomputing.Profile

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobilecomputing.MainActivity
import com.google.accompanist.insets.systemBarsPadding

@Composable
fun EditProfile(
    sharedPreferences: SharedPreferences,
    navController: NavController,
    onBackPress : () -> Unit,
) {
    Surface {
        var oldusername by remember { mutableStateOf("") }
        var oldpassword by remember { mutableStateOf("") }
        var newusername by remember { mutableStateOf("") }
        var newpassword by remember { mutableStateOf("") }
        val context = LocalContext.current

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
                Text(
                    text = "Edit Your Profile",
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
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "First type your old",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
                )
            Text(
                text =" Username / Password :",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            OutlinedTextField(
                value = oldusername,
                onValueChange = { oldusername = it },
                label = { Text("Old Username") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
            OutlinedTextField(
                value = oldpassword,
                onValueChange = { oldpassword = it },
                label = { Text("Old Password") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Now type your new",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text =" Username / Password :",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            OutlinedTextField(
                value = newusername,
                onValueChange = { newusername = it },
                label = { Text("New Username") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
            OutlinedTextField(
                value = newpassword,
                onValueChange = { newpassword = it },
                label = { Text("New Password") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
            Spacer(modifier = Modifier.height(100.dp))
            Button(
                onClick = {
                    if (savechanges(sharedPreferences = sharedPreferences,
                        navController = navController,
                        oldusername = oldusername,
                        oldpassword = oldpassword,
                        newusername = newusername,
                        newpassword = newpassword,
                        )){
                        Toast.makeText(context, "Changes Done !", Toast.LENGTH_SHORT).show()
                    } else
                        Toast.makeText(context, "Try again...", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Save changes",
                    fontWeight = FontWeight.Bold,
                    color = Color.LightGray,
                    fontSize = 22.sp
                )
            }
        }
    }
}

fun savechanges(
    sharedPreferences: SharedPreferences,
    navController: NavController,
    oldusername: String,
    oldpassword: String,
    newusername: String,
    newpassword: String
): Boolean {
    return if(sharedPreferences.contains(oldusername)){

        sharedPreferences.edit().remove(oldusername).apply()
        sharedPreferences.edit().remove(oldpassword).apply()
        sharedPreferences.edit().putString(newusername,newpassword).apply()
        sharedPreferences.apply { navController.navigate("profile") }
        true

    } else
        false
}



