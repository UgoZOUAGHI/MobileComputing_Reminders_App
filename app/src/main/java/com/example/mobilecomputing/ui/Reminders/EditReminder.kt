package com.example.mobilecomputing.ui.Reminders

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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

    val appReminderHour = viewState.reminder?.reminder_hour ?: ""
    val reminderHour = rememberSaveable { mutableStateOf("") }
    reminderHour.value = appReminderHour

    val appReminderSeen = viewState.reminder?.reminder_seen ?: ""
    val reminderSeen = rememberSaveable { mutableStateOf("") }
    reminderSeen.value = appReminderSeen.toString()

    val seconds = rememberSaveable{ mutableStateOf("") }
    val minutes = rememberSaveable{ mutableStateOf("") }
    val hours = rememberSaveable { mutableStateOf("") }


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
            Spacer(modifier = Modifier.height(30.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "The actual Timer : " + reminderHour.value,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Column(
                    modifier = Modifier.width(110.dp)
                ) {
                    OutlinedTextField(
                        value = hours.value,
                        onValueChange = { hours.value = it },
                        label = { Text(text = "Hours") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        //modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.width(110.dp)
                ) {
                    OutlinedTextField(
                        value = minutes.value,
                        onValueChange = { minutes.value = it },
                        label = { Text(text = "Minutes") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        //modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.width(110.dp)
                ) {
                    OutlinedTextField(
                        value = seconds.value,
                        onValueChange = { seconds.value = it },
                        label = { Text(text = "Seconds") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }
            }


            Spacer(modifier = Modifier.height(100.dp))
            Button(
                onClick = {
                    coroutineScope.launch {
                        if(hours.value == ""){hours.value="0"}
                        if(minutes.value == ""){minutes.value="0"}
                        if(seconds.value == ""){seconds.value="0"}
                        viewModel.saveReminder(
                        Reminder(
                            reminderId = reminderId,
                            message = message.value,
                            reminder_time = (((hours.value.toLong()*60*60)+minutes.value.toLong())*60+seconds.value.toLong()).toString(),
                            creation_time = Date().time,
                            location_x = "",
                            location_y = "",
                            creator_id = 0,
                            reminder_seen = if(reminderSeen.value.toInt() == 1 || (((hours.value.toLong()*60*60)+minutes.value.toLong())*60+seconds.value.toLong()).toString() != "0"){
                                0
                            }else{
                                1
                                 },
                            reminder_hour = hours.value + "h" + minutes.value + "m" + seconds.value + "s"
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