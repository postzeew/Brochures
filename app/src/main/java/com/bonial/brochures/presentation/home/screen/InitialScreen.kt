package com.bonial.brochures.presentation.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bonial.brochures.R
import com.bonial.brochures.presentation.home.HomeEvent

@Composable
fun InitialScreen(
    onEvent: (HomeEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Button(
            onClick = { onEvent.invoke(HomeEvent.LOAD_BUTTON_CLICK) },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(stringResource(R.string.load_brochures))
        }
    }
}

@Preview
@Composable
private fun InitialScreenPreview() {
    InitialScreen(onEvent = {})
}