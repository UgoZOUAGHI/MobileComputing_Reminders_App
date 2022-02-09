package com.example.mobilecomputing.ui.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilecomputing.Data.Entity.Reminder
import com.example.mobilecomputing.Data.Repository.ReminderRepository
import com.example.mobilecomputing.Graph
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReminderViewModel(
    private val reminderRepository: ReminderRepository = Graph.reminderRepository,
): ViewModel() {
    private val _state = MutableStateFlow(ReminderViewState())

    val state: StateFlow<ReminderViewState>
        get() = _state

    suspend fun saveReminder(reminder: Reminder): Long {
        return reminderRepository.addReminder(reminder)
    }

    init {
        viewModelScope.launch {
            reminderRepository.reminders().collect { reminders ->
                _state.value = ReminderViewState(reminders)
            }
        }
    }
}

data class ReminderViewState(
    val reminders: List<Reminder> = emptyList()
)