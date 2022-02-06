package com.example.mobilecomputing.Repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStorePreferenceRepository(context: Context) {
    private val dataStore: DataStore<Preferences> = context.createDataStore(name = "SampleData")

    private val userNameDefault = "admin"
    private val userPasswordDefault = "admin"

    companion object {
        val PREF_USERNAME = preferencesKey<String>("user_name")
        val PREF_PASSWORD = preferencesKey<String>("pass_word")

        private var INSTANCE: DataStorePreferenceRepository? = null

        fun getInstance(context: Context): DataStorePreferenceRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let {
                    return it
                }
                val instance = DataStorePreferenceRepository(context)
                INSTANCE = instance
                instance
            }
        }
    }

    //setValue
    suspend fun setUserName(userName: String) {
        dataStore.edit { preferences ->
            preferences[PREF_USERNAME] = userName
        }
    }

    suspend fun setPassword(password: String) {
        dataStore.edit { preferences ->
            preferences[PREF_PASSWORD] = password
        }
    }

    //getValue
    val getUserName: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[PREF_USERNAME] ?: userNameDefault
        }

    //getValue
    val getUserPassword: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[PREF_PASSWORD] ?: userPasswordDefault
        }
}














