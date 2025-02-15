package com.example.gamesretrofit.data

import com.example.gamesretrofit.model.GamesModel
import com.example.gamesretrofit.model.SingleGameModel
import com.example.gamesretrofit.utils.Constants.Companion.API_KEY
import com.example.gamesretrofit.utils.Constants.Companion.ENDPOINT
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiGames {

    @GET(ENDPOINT+API_KEY)
    suspend fun getGames(): retrofit2.Response<GamesModel>

    @GET("${ENDPOINT}/{id}${API_KEY}")
    suspend fun getGameById(@Path(value = "id")id: Int): retrofit2.Response<SingleGameModel>
}