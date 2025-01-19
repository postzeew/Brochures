package com.bonial.brochures.presentation.home.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bonial.brochures.presentation.home.HomeEvent
import com.bonial.brochures.presentation.home.HomeState


@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    when (state) {
        HomeState.Initial -> InitialScreen(onEvent, modifier)
        HomeState.Loading -> LoaderScreen(modifier)
        is HomeState.Success -> BrochuresScreen(state, onEvent, modifier)
        is HomeState.Error -> ErrorScreen(state, onEvent, modifier)
    }
}