package com.example.mobilecomputing.Login

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobilecomputing.R
import com.google.accompanist.insets.systemBarsPadding
import androidx.test.core.app.ApplicationProvider.getApplicationContext

import android.content.SharedPreferences
import android.widget.Toast
import androidx.test.core.app.ApplicationProvider


@Composable
 fun Login(
    sharedPreferences: SharedPreferences,
    navController: NavController
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .systemBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.AccountBox,
                contentDescription = null,
                modifier = Modifier.size(250.dp)
            )
            Spacer(modifier = Modifier.height(70.dp))
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username")},
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password")},
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
                Button(
                onClick = {
                    check(sharedPreferences = sharedPreferences, navController = navController, username = username, password = password)
                          },
                enabled = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(52.dp),
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = "Login",
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.LightGray,
                )
            }
                Spacer(modifier = Modifier.size(10.dp))
                Button(
                    onClick = { navController.navigate("register")},
                    enabled = true,
                    modifier = Modifier
                        .width(106.dp)
                        .size(42.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.onBackground),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = "Register",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray
                    )
                }

            IconButton( onClick = {navController.navigate(route = "tutorial")} ) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = null)
            }
            }
        }
    }

fun check(
    sharedPreferences: SharedPreferences,
    navController: NavController,
    username: String,
    password: String,
){
    if(sharedPreferences.contains(username)){
        val passwordfrompref = sharedPreferences.getString(username, "")
        if(passwordfrompref == password){
            navController.navigate("home")
        }
    }
}