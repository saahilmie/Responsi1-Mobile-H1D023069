package com.example.responsi1mobileh1d023069.network

import com.example.responsi1mobileh1d023069.model.TeamResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface FootballApiService {

    @GET("teams/{id}")
    suspend fun getTeamById(@Path("id") teamId: Int): TeamResponse

    companion object {
        private const val BASE_URL = "https://api.football-data.org/v4/"
        private const val API_TOKEN = "950f33495fa2498ea8afd7679a91d3fc"

        fun create(): FootballApiService {
            // Logging interceptor
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            // Auth interceptor untuk menambahkan token di header
            val authInterceptor = Interceptor { chain ->
                val originalRequest = chain.request()
                val requestWithAuth = originalRequest.newBuilder()
                    .header("X-Auth-Token", API_TOKEN)
                    .build()
                chain.proceed(requestWithAuth)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(FootballApiService::class.java)
        }
    }
}