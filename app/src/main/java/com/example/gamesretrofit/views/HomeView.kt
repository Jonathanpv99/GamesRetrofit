package com.example.gamesretrofit.views

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gamesretrofit.components.CardGame
import com.example.gamesretrofit.components.LoadinPage
import com.example.gamesretrofit.components.MainTopBar
import com.example.gamesretrofit.viewModel.GamesViewModel
import kotlinx.coroutines.delay

@Composable
fun HomeView(
    viewModel: GamesViewModel,
    navController: NavController
    ){

    var isLoading by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        delay(500)
        isLoading = false
    }

    Scaffold(
        topBar = {
            MainTopBar(
                title = "APIGAMES",
                onClickBackButton = {}
            ) {
                navController.navigate("SearchView")
            }
        }
    ) {
        if (isLoading){
            LoadinPage()
        }else{
            ContentHomeView(viewModel, it, navController)
        }
    }

}

@Composable
fun ContentHomeView(
    viewModel: GamesViewModel,
    paddingValues: PaddingValues,
    navController: NavController
){
    val games by viewModel.games.collectAsState()
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
    ) {
        items(games){item ->
            CardGame(item) {
                navController.navigate("DetailsView/${item.id.toInt()}")
            }
            Text(
                text = item.name,
                fontWeight = FontWeight.ExtraBold,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                modifier = Modifier.padding(start = 10.dp)
                )
        }
    }
}