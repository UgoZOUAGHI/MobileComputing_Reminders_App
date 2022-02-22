package com.example.mobilecomputing.ui.Reminders

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.systemBarsPadding
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobilecomputing.Data.Entity.Reminder
import java.util.*

@Composable
fun Reminders(
    onBackPress: () -> Unit,
    viewModel: ReminderViewModel = viewModel()
) {
    Surface {
        val viewState by viewModel.state.collectAsState()
        val coroutineScope = rememberCoroutineScope()
        val message = rememberSaveable { mutableStateOf("") }
        val time = rememberSaveable{ mutableStateOf("") }
        val seconds = rememberSaveable{ mutableStateOf("") }
        val minutes = rememberSaveable{ mutableStateOf("") }
        val hours = rememberSaveable{ mutableStateOf("") }
        /*val ReminderName = rememberSaveable { mutableStateOf("") }
        val ReminderDesc = rememberSaveable { mutableStateOf("") }
        val ReminderDate = rememberSaveable { mutableStateOf("") }*/
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
                Text(text = "New Reminder",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.LightGray,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.padding(16.dp)
            ) {
                Row() {
                    OutlinedTextField(
                        value = message.value,
                        onValueChange = { message.value = it },
                        label = { Text(text = "Reminder name") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                /*Spacer(modifier = Modifier.height(70.dp))
                OutlinedTextField(
                    value = ReminderDesc.value,
                    onValueChange = { ReminderDesc.value = it },
                    label = { Text(text = "Reminder description")},
                    modifier = Modifier.fillMaxWidth()
                        .height(200.dp)
                )
                */


                val withNotif = remember { mutableStateOf(true)}
                val withVibration = remember { mutableStateOf(true)}

                if(withNotif.value){
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Please enter the time before being notified :",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

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
                }
                Spacer(modifier = Modifier.height(100.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = withNotif.value,
                        onCheckedChange = { withNotif.value = it},
                    )
                    Text(
                        text = "With Notification"
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = withVibration.value,
                        onCheckedChange = { withVibration.value = it},
                    )
                    Text(
                        text = "With Vibration"
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))
                Button(
                    onClick = {
                        coroutineScope.launch {
                            if(hours.value == "" || !withNotif.value){hours.value="0"}
                            if(minutes.value == "" || !withNotif.value){minutes.value="0"}
                            if(seconds.value == "" || !withNotif.value){seconds.value="0"} //bc if i let them empty = app crash
                            viewModel.saveReminder(
                                Reminder(
                                    message = message.value,
                                    location_x = "",
                                    location_y = "",
                                    reminder_time = (((hours.value.toLong()*60*60)+minutes.value.toLong())*60+seconds.value.toLong()).toString(),
                                    creation_time = Date().time,
                                    creator_id = 0,
                                    reminder_seen = if((((hours.value.toLong()*60*60)+minutes.value.toLong())*60+seconds.value.toLong()).toInt() == 0){
                                        1
                                    }else{
                                        0
                                    },
                                    reminder_hour = hours.value + "h" + minutes.value + "m" + seconds.value + "s",
                                    reminder_vibration = true
                                )
                            )
                        }
                        onBackPress() },
                    ) {
                    Text(text = "Set reminder",
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray,
                        fontSize = 22.sp
                    )
                }
            }
        }
    }
}