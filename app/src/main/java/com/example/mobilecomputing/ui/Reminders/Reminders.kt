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
                OutlinedTextField(
                    value = message.value,
                    onValueChange = { message.value = it },
                    label = { Text(text = "Reminder name")},
                    modifier = Modifier.fillMaxWidth()
                )
                /*Spacer(modifier = Modifier.height(70.dp))
                OutlinedTextField(
                    value = ReminderDesc.value,
                    onValueChange = { ReminderDesc.value = it },
                    label = { Text(text = "Reminder description")},
                    modifier = Modifier.fillMaxWidth()
                        .height(200.dp)
                )
                */
                Spacer(modifier = Modifier.height(20.dp))
                Row{
                    OutlinedTextField(
                        value = time.value,
                        onValueChange = { time.value = it },
                        label = { Text(text = "Reminder Time")},
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                    )

                }
                Spacer(modifier = Modifier.height(100.dp))
                Button(
                    onClick = {
                        coroutineScope.launch {
                            viewModel.saveReminder(
                                com.example.mobilecomputing.Data.Entity.Reminder(
                                    message = message.value,
                                    location_x = "",
                                    location_y = "",
                                    reminder_time = time.value,
                                    creation_time = Date().time,
                                    creator_id = 0,
                                    reminder_seen = 0
                                )
                            )
                        }
                        onBackPress() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(50.dp)
                ) {
                    Text(text = "Set reminder",
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}