package com.example.mobilecomputing.ui.Home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.example.mobilecomputing.Data.Entity.Reminder
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.ui.graphics.Color.Companion.LightGray
import com.example.mobilecomputing.Graph
import com.example.mobilecomputing.Graph.reminderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlin.math.pow

@Composable
fun CategoryReminder(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    navController: NavController,
    latlng: LatLng?
) {
    val viewState by viewModel.state.collectAsState()

    Column(modifier = modifier) {

        val seeAll = remember { mutableStateOf(false) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(
                checked = seeAll.value,
                modifier = Modifier.padding(top = 2.dp, start = 10.dp),
                onCheckedChange = { seeAll.value = it }
            )
            Text(
                text = "See all",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            if (latlng == null) {
                Button(
                    onClick = { navController.navigate("map") },
                    modifier = Modifier.padding(top = 2.dp, start = 90.dp),
                ) {
                    Text(text = "Virtual Location",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = LightGray
                    )
                }
            }
            else {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Button(
                        onClick = { navController.navigate("map") },
                        modifier = Modifier.padding(top = 2.dp, start = 110.dp),
                    ) {
                        Text(text = "Change V-Loc",
                            fontSize = 18.sp,
                            color = LightGray
                        )
                    }
                }
            }
        }
            ReminderList(
                list = viewState.reminders,
                viewModel = viewModel,
                navController = navController,
                seeAll = seeAll.value,
                latlng = latlng
            )
    }
}

@Composable
private fun ReminderList(
    list: List<Reminder>,
    viewModel: HomeViewModel,
    navController: NavController,
    seeAll: Boolean,
    latlng: LatLng?
) {
    LazyColumn(
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.Center
    ) {
        items(list) { item ->
        if(seeAll || item.reminder_seen.toInt() == 1 && closeLocation(item, latlng)){
            val closeLoc = closeLocation(item, latlng)
                ReminderListItem(
                    reminder = item,
                    viewModel = viewModel,
                    onClick = {},
                    modifier = Modifier.fillParentMaxWidth(),
                    navController = navController,
                    closeLoc = closeLoc
                )
            }
        }
    }
}


@Composable
private fun ReminderListItem(
    reminder: Reminder,
    viewModel: HomeViewModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController,
    closeLoc: Boolean
) {
    ConstraintLayout(modifier = modifier.clickable { onClick() }) {
        val (divider, remainderMessage, paymentCategory, iconEdit, iconDelete, date, hour) = createRefs()
        Divider(
            Modifier.constrainAs(divider) {
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
                width = Dimension.fillToConstraints
            }
        )

        val coroutineScope = rememberCoroutineScope()

        Text(
            text = reminder.message,
            maxLines = 1,
            style = MaterialTheme.typography.subtitle1,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(remainderMessage) {
                linkTo(
                    start = parent.start,
                    end = iconDelete.start,
                    startMargin = 8.dp,
                    endMargin = 16.dp,
                    bias = 0f
                )
                top.linkTo(parent.top, margin = 10.dp)
                width = Dimension.preferredWrapContent
            }
        )
        var message = ""
        message += if(reminder.reminder_time.toInt() == 0){
            "X Notification"
        }else{
            reminder.reminder_hour
        }
        message += if(closeLoc){
            if(reminder.location_x != 0.0 && reminder.location_y != 0.0) {
                " / V Location"
            }else{
                updateSeen(reminder)
                " / X Location"
            }
        }else{
            if(reminder.location_x != 0.0 && reminder.location_y != 0.0) {
                " / X Location"
            }else{
                updateSeen(reminder)
                " / X Location"
            }
        }

        Text(
            text = message,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.caption,
            fontSize = 12.sp,
            modifier = Modifier
                .constrainAs(hour) {
                    linkTo(
                        start = paymentCategory.end,
                        end = iconEdit.start,
                        startMargin = 8.dp,
                        endMargin = 16.dp,
                        bias = 0f
                    )
                    centerHorizontallyTo(paymentCategory)
                    absoluteLeft.linkTo(paymentCategory.absoluteRight, 1.dp)
                    bottom.linkTo(parent.bottom, 10.dp)
                }
                .padding(start = 8.dp)
        )
        Text(
            text = reminder.creation_time.toDateString(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 11.sp,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .constrainAs(date) {
                linkTo(
                    start = paymentCategory.end,
                    end = iconEdit.start,
                    startMargin = 8.dp,
                    endMargin = 16.dp,
                    bias = 0f
                )
                centerVerticallyTo(paymentCategory)
                top.linkTo(remainderMessage.bottom, 8.dp)
                bottom.linkTo(parent.bottom, 10.dp)
            }

        )

        IconButton(
            onClick = {
                navController.navigate(route = "editreminder/${reminder.reminderId}")
            },
            modifier = Modifier
                .size(25.dp)
                .padding(1.dp)
                .constrainAs(iconEdit) {
                    top.linkTo(parent.top, 10.dp)
                    bottom.linkTo(parent.bottom, 10.dp)
                    end.linkTo(parent.end, 50.dp)
                }
        ) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = null
            )
        }

        IconButton(
            onClick = {
                coroutineScope.launch {
                    viewModel.deleteReminder(reminder.reminderId)
                }
            },
            modifier = Modifier
                .size(50.dp)
                .padding(6.dp)
                .constrainAs(iconDelete) {
                    top.linkTo(parent.top, 10.dp)
                    bottom.linkTo(parent.bottom, 10.dp)
                    end.linkTo(parent.end, 5.dp)
                }
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = null
            )
        }
    }
}

fun updateSeen(reminder: Reminder) {
    if(reminder.location_x == 0.0 && reminder.location_y == 0.0){
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            reminderRepository.updateSeenBack(reminder.reminderId)
        }
    }else{}
}

private fun Date.formatToString(): String {
    return SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(this)
}

fun Long.toDateString(): String {
    return SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(Date(this))
}

private fun closeLocation(reminder: Reminder, latlng: LatLng?): Boolean {
    val x = ((latlng?.latitude ?: reminder.location_x) - reminder.location_x).pow(2)
    val y = ((latlng?.longitude ?: reminder.location_y) - reminder.location_y).pow(2)
    val distance = kotlin.math.sqrt(x + y)

    if(reminder.location_x == 0.0 && reminder.location_y == 0.0){
        return true
    }

    return distance < 0.05
}