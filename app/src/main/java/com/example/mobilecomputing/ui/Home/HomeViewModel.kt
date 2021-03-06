package com.example.mobilecomputing.ui.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilecomputing.Data.Entity.Reminder
import com.example.mobilecomputing.Data.Repository.ReminderRepository
import com.example.mobilecomputing.Graph
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val reminderRepository: ReminderRepository = Graph.reminderRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(HomeViewState())

    val state: StateFlow<HomeViewState>
        get() = _state

    suspend fun deleteReminder(id :Long){
        reminderRepository.removeReminderById(id)
    }

    init {
        viewModelScope.launch {
            reminderRepository.reminders().collect { reminders ->
                    _state.value = HomeViewState(reminders)
            }
        }
    }
}

data class HomeViewState(
    var reminders: List<Reminder> = emptyList()
)