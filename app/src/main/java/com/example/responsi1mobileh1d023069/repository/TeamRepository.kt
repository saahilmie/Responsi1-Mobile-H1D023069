package com.example.responsi1mobileh1d023069.repository

import com.example.responsi1mobileh1d023069.model.TeamResponse
import com.example.responsi1mobileh1d023069.network.FootballApiService

class TeamRepository(private val apiService: FootballApiService) {

    suspend fun getTeamData(teamId: Int): Result<TeamResponse> {
        return try {
            val response = apiService.getTeamById(teamId)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    companion object {
        @Volatile
        private var instance: TeamRepository? = null

        fun getInstance(): TeamRepository {
            return instance ?: synchronized(this) {
                instance ?: TeamRepository(FootballApiService.create()).also {
                    instance = it
                }
            }
        }
    }
}