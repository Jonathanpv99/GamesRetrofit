package com.example.gamesretrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gamesretrofit.ui.theme.GamesRetrofitTheme
import com.example.gamesretrofit.viewModel.GamesViewModel
import com.example.gamesretrofit.views.HomeView
import dagger.hilt.android.AndroidEntryPoint
//para injeccion de dependencias
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel : GamesViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            GamesRetrofitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   HomeView(viewModel)
                }
            }
        }
    }
}