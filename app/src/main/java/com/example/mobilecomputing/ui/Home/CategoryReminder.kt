package com.example.mobilecomputing.ui.Home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.example.mobilecomputing.Data.Entity.Reminder
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CategoryReminder(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val viewState by viewModel.state.collectAsState()

    Column(modifier = modifier) {
        ReminderList(
            list = viewState.reminders,
            viewModel = viewModel,
            navController = navController
        )
    }
}

@Composable
private fun ReminderList(
    list: List<Reminder>,
    viewModel: HomeViewModel,
    navController: NavController,
) {
    LazyColumn(
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.Center
    ) {
        items(list) { item ->
            ReminderListItem(
                reminder = item,
                viewModel = viewModel,
                onClick = {},
                modifier = Modifier.fillParentMaxWidth(),
                navController = navController,
            )
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

        Text(
            text = reminder.reminder_time,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.caption,
            fontSize = 15.sp,
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

private fun Date.formatToString(): String {
    return SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(this)
}

private fun Long.toDateString(): String {
    return SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(Date(this))
}