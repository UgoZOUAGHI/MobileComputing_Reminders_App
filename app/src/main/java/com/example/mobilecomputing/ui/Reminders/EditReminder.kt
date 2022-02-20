package com.example.mobilecomputing.ui.Reminders

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobilecomputing.Data.Entity.Reminder
import com.example.mobilecomputing.ui.Profile.savechanges
import com.example.mobilecomputing.util.viewModelProviderFactoryOf
import com.google.accompanist.insets.systemBarsPadding
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun EditReminder(
    onBackPress: () -> Unit,
    reminderId: Long
) {
    val viewModel: EditReminderViewModel = viewModel(
        key = "reminder_id_$reminderId",
        factory = viewModelProviderFactoryOf {
            EditReminderViewModel(reminderId)
        }
    )
    val viewState by viewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    val appMessage = viewState.reminder?.message ?: ""
    val message = rememberSaveable { mutableStateOf("") }
    message.value = appMessage

    val appReminderTime = viewState.reminder?.reminder_time ?: ""
    val reminderTime = rememberSaveable { mutableStateOf("") }
    reminderTime.value = appReminderTime

    Surface(

    ){
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
                    text = "Edit A Reminder",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ){
            Spacer(modifier = Modifier.height(50.dp))
            OutlinedTextField(
                value = message.value,
                onValueChange = { message.value = it },
                label = { Text(text = "Message")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = reminderTime.value,
                onValueChange = { reminderTime.value = it },
                label = { Text(text = "Reminder Time")},
                modifier = Modifier.fillMaxWidth()
            )


            Spacer(modifier = Modifier.height(100.dp))
            Button(
                onClick = {
                    coroutineScope.launch { viewModel.saveReminder(
                        Reminder(
                            reminderId = reminderId,
                            message = message.value,
                            reminder_time = reminderTime.value,
                            creation_time = Date().time,
                            location_x = "",
                            location_y = "",
                            creator_id = 0,
                            reminder_seen = viewState.reminder?.reminder_seen ?: 0,
                        )
                    )
                        Toast.makeText(context, "Reminder Modified !", Toast.LENGTH_SHORT).show()
                    }
                    onBackPress()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .size(52.dp)
            ) {
                Text(text = "Save modifications",
                    fontWeight = FontWeight.Bold,
                    color = Color.LightGray,
                    fontSize = 22.sp
                )
            }
        }
    }
}