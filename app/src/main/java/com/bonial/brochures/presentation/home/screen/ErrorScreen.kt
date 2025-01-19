package com.bonial.brochures.presentation.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bonial.brochures.R
import com.bonial.brochures.presentation.home.HomeEvent

@Composable
fun ErrorScreen(
    message: String,
    onEvent: (HomeEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize(),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = message)

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { onEvent.invoke(HomeEvent.RETRY_BUTTON_CLICK) }) {
                Text(stringResource(R.string.retry))
            }
        }
    }
}

@Preview
@Composable
private fun ErrorScreenPreview() {
    ErrorScreen(
        message = "Smth went wrong",
        onEvent = {}
    )
}