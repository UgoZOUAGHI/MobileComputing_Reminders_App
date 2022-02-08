package com.example.mobilecomputing.Login

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.test.core.app.ApplicationProvider
import com.example.mobilecomputing.R

@SuppressLint("CommitPrefEdits")
@Composable
fun Register(
    sharedPreferences: SharedPreferences,
    navController: NavController,
    onBackPress: () -> Unit,
) {
    TopAppBar(
        title = {
            IconButton(
                onClick = onBackPress
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
            Text(
                text = "Create an account",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.LightGray,
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        actions = {
            IconButton(onClick = { navController.navigate(route = "login") }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = stringResource(R.string.logout)
                )
            }

        }
    )

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val editor = sharedPreferences.edit()
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(16.dp)
            .padding(top = 50.dp)
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                editor.putString(username,password).apply().apply {
                    navController.navigate("login")
                    Toast.makeText(context, "Registered !", Toast.LENGTH_SHORT).show()
                } //fait le reste tant ce quil y a entre {}
                      },
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .size(42.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Text(text = "Register",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.LightGray,
            )}

    }
}
