package com.example.mobilecomputing

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.test.core.app.ApplicationProvider
import com.example.mobilecomputing.ui.theme.MobileComputingTheme

class MainActivity : ComponentActivity()
{
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences(
            getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
        sharedPreferences.edit().putString("adminkey", "admin").apply()

        setContent {
            MobileComputingTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MobileComputingApp(sharedPreferences)
                }

            }
        }
    }
}

