package com.example.mobilecomputing.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilecomputing.Repository.DataStorePreferenceRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DataStoreViewModel(
    private val dataStorePreferenceRepository: DataStorePreferenceRepository
): ViewModel() {
    private val _userName = MutableLiveData("")
    private val _password = MutableLiveData("")

    val userName: LiveData<String> = _userName
    val userPassword: LiveData<String> = _password

    init {
        viewModelScope.launch {
            dataStorePreferenceRepository.getUserName.collect {
                _userName.value = it
                _password.value = it
            }
        }
    }

    suspend fun saveUserName(userName: String) {
        dataStorePreferenceRepository.setUserName(userName)
    }
    suspend fun saveUserPassword(userPassword: String) {
        dataStorePreferenceRepository.setPassword(userPassword)
    }
}














