package com.example.mobilecomputing.Data.Repository

import com.example.mobilecomputing.Data.Dao.ReminderDao
import com.example.mobilecomputing.Data.Entity.Reminder
import kotlinx.coroutines.flow.Flow

class ReminderRepository (
    private val reminderDao: ReminderDao
){
    suspend fun addReminder(reminder: Reminder) = reminderDao.insert(reminder)
    fun reminders(): Flow<List<Reminder>> = reminderDao.reminders()
    suspend fun removeReminderById(id: Long) = reminderDao.deleteById(id)
    suspend fun updateReminder(reminder: Reminder) = reminderDao.update(reminder)
    suspend fun getReminder(id: Long) = reminderDao.reminder(id)
}