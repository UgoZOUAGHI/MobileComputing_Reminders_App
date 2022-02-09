package com.example.mobilecomputing.ui.Reminders

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.systemBarsPadding
import kotlinx.coroutines.launch
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Reminders(
    onBackPress: () -> Unit,
    viewModel: ReminderViewModel = viewModel()
) {
    Surface {
        val viewState by viewModel.state.collectAsState()
        val coroutineScope = rememberCoroutineScope()
        val message = rememberSaveable { mutableStateOf("") }
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
                Spacer(modifier = Modifier.height(20.dp))
                Row{
                    OutlinedTextField(
                        value = ReminderDate.value,
                        onValueChange = { ReminderDate.value = it },
                        label = { Text(text = "Date")},
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                    )

                }*/
                Spacer(modifier = Modifier.height(100.dp))
                Button(
                    onClick = {
                        coroutineScope.launch {
                            viewModel.saveReminder(
                             com.example.mobilecomputing.Data.Entity.Reminder(
                                 message = message.value,
                                 location_x = "",
                                 location_y = "",
                                 reminder_time = "",
                                 creation_time = "",
                                 creator_id = 0,
                                 reminder_seen = 0,
                            )
                        )
                    }
                        onBackPress() },
                    modifier = Modifier.width(150.dp).size(40.dp)
                ) {
                    Text(text = "Set reminder",
                    fontWeight = FontWeight.Bold,
                        color = Color.LightGray,
                    )
                }
            }
        }
    }
}