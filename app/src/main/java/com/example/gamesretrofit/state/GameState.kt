package com.example.gamesretrofit.state

data class GameState(
    val name: String = "",
    val description_raws: String ="",
    val metacritic: Int = 0,
    val website: String ="",
    val background_image: String =""
)
