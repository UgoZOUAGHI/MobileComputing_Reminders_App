package com.example.mobilecomputing.Data.Repository

import com.example.mobilecomputing.Data.Room.ReminderDao
import com.example.mobilecomputing.Data.Entity.Reminder
import kotlinx.coroutines.flow.Flow

class ReminderRepository (
    private val reminderDao: ReminderDao
){
    fun reminders(): Flow<List<Reminder>> = reminderDao.reminders()
    suspend fun addReminder(reminder: Reminder) = reminderDao.insert(reminder)
    suspend fun removeReminderById(id: Long) = reminderDao.deleteById(id)
    suspend fun updateReminder(reminder: Reminder) = reminderDao.update(reminder)
    suspend fun getReminder(id: Long) = reminderDao.reminder(id)
    suspend fun updateSeen(id: Long) = reminderDao.updateSeen(id)
    suspend fun updateSeenBack(id: Long) = reminderDao.updateSeenBack(id)
}