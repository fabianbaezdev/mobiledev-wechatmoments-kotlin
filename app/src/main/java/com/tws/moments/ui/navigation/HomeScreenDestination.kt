package com.tws.moments.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.tws.moments.presentation.viewmodels.MainViewModel
import com.tws.moments.ui.screens.HomeScreen

@Composable
fun HomeScreenDestination() {
    val viewModel = hiltViewModel<MainViewModel>()
    HomeScreen(
        viewModel = viewModel
    )
}