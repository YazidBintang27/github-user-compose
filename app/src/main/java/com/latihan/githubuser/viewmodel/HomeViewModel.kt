package com.latihan.githubuser.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.latihan.githubuser.models.UserModel
import com.latihan.githubuser.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    private val _userData = MutableStateFlow(emptyList<UserModel.Item?>())
    private val _userInput = MutableStateFlow("")
    private val _isLoading = MutableStateFlow(false)

    val userData: StateFlow<List<UserModel.Item?>>
        get() = _userData
    val isLoading: StateFlow<Boolean>
        get() = _isLoading
    val userInput: StateFlow<String>
        get() = _userInput

    fun getUsers(q: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _userData.value = userRepository.requestUser(q)
            _isLoading.value = false
        }
    }
}