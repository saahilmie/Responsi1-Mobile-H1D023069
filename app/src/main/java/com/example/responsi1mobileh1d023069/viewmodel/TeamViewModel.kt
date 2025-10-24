package com.example.responsi1mobileh1d023069.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.responsi1mobileh1d023069.model.TeamResponse
import com.example.responsi1mobileh1d023069.repository.TeamRepository
import kotlinx.coroutines.launch

class TeamViewModel : ViewModel() {

    private val repository = TeamRepository.getInstance()

    private val _teamData = MutableLiveData<TeamResponse>()
    val teamData: LiveData<TeamResponse> = _teamData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadTeamData(teamId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.getTeamData(teamId)
            _isLoading.value = false

            result.onSuccess { team ->
                _teamData.value = team
            }.onFailure { exception ->
                _error.value = exception.message ?: "Unknown error occurred"
            }
        }
    }
}