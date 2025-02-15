package com.example.gamesretrofit.views

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gamesretrofit.components.LoadinPage
import com.example.gamesretrofit.components.MainImage
import com.example.gamesretrofit.components.MainTopBar
import com.example.gamesretrofit.components.MetaWebSite
import com.example.gamesretrofit.components.ReviewCard
import com.example.gamesretrofit.utils.Constants.Companion.CUSTOM_BLACK
import com.example.gamesretrofit.viewModel.GamesViewModel
import kotlinx.coroutines.delay

@Composable
fun DetailsView(
    viewModel: GamesViewModel,
    navController: NavController,
    id: Int
){
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.getGameById(id)
        delay(1000)
        isLoading = false
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.clean()
        }
    }
    Scaffold(
        topBar =  {
            MainTopBar(
                title = viewModel.state.name,
                showBackButton = true,
                onClickBackButton = {
                    navController.popBackStack()
                }
            ) {}
        }
    ) {
        if (isLoading){
            LoadinPage()
        }else{
            ContentDetailsView(it, viewModel)
        }
    }
}

@Composable
fun ContentDetailsView(
    pad: PaddingValues,
    viewModel: GamesViewModel
){
    val state = viewModel.state

    Column(
        modifier = Modifier
            .padding(pad)
            //.background(Color(CUSTOM_BLACK))
    ) {
        MainImage(img = viewModel.state.background_image)
        Spacer(modifier = Modifier.padding(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 5.dp)
        ) {
            MetaWebSite(state.website)
            ReviewCard(state.metacritic)
        }

        val scroll = rememberScrollState(0)
        Text(
            text = state.description_raws,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
                .verticalScroll(scroll)

        )
    }
}