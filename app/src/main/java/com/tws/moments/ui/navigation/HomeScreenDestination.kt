package com.tws.moments.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tws.moments.data.MomentRepository
import com.tws.moments.presentation.viewmodels.MainViewModelFactory
import com.tws.moments.ui.screens.HomeScreen

@Composable
fun HomeScreenDestination() {
    // val viewModel = hiltViewModel<ListViewModel>()
    val repository = MomentRepository()
    HomeScreen(
        viewModel = viewModel(factory = MainViewModelFactory(repository))
    )
}