package com.example.gamesretrofit.data

import com.example.gamesretrofit.model.GamesModel
import com.example.gamesretrofit.utils.Constants.Companion.API_KEY
import com.example.gamesretrofit.utils.Constants.Companion.ENDPOINT
import okhttp3.Response
import retrofit2.http.GET

interface ApiGames {

    @GET(ENDPOINT+API_KEY)
    suspend fun getGames(): retrofit2.Response<GamesModel>
}