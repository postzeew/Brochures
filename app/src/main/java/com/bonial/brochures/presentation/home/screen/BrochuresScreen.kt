package com.bonial.brochures.presentation.home.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bonial.brochures.R
import com.bonial.brochures.domain.model.BrochureVo
import com.bonial.brochures.domain.model.BrochureVo.TypeVo.PREMIUM
import com.bonial.brochures.domain.model.BrochureVo.TypeVo.USUAL
import com.bonial.brochures.presentation.home.HomeEvent
import com.bonial.brochures.presentation.home.HomeState
import com.bonial.brochures.presentation.home.ListFilterType.CLOSER_THAN_5_KM
import com.bonial.brochures.presentation.home.ListFilterType.NO_FILTER

private const val IMAGE_ASPECT_RATIO = 0.75F

@Composable
fun BrochuresScreen(
    state: HomeState.Success,
    onEvent: (HomeEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val columnsCount = when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> 2
        Configuration.ORIENTATION_LANDSCAPE -> 3
        else -> 2
    }

    Column(modifier = modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columnsCount),
            contentPadding = PaddingValues(vertical = 8.dp),
            modifier = Modifier.weight(1F)
        ) {
            items(
                items = state.brochures,
                key = { item -> item.id },
                span = { item ->
                    when (item.type) {
                        USUAL -> GridItemSpan(1)
                        PREMIUM -> GridItemSpan(columnsCount)
                    }
                }
            ) { item -> Brochure(item) }
        }

        Button(
            onClick = { onEvent.invoke(HomeEvent.SWITCH_FILTER_STATE_BUTTON_CLICK) },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        ) {
            Text(
                text = when (state.listFilterType) {
                    NO_FILTER -> stringResource(R.string.filter_text)
                    CLOSER_THAN_5_KM -> stringResource(R.string.clear_filter_text)
                }
            )
        }
    }
}

@Composable
private fun Brochure(item: BrochureVo, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp)
    ) {
        when (item.image) {
            BrochureVo.ImageVo.PlaceholderVo -> {
                Image(
                    painter = painterResource(id = R.drawable.image_placeholder),
                    contentDescription = null,
                    modifier = Modifier.aspectRatio(IMAGE_ASPECT_RATIO)
                )
            }

            is BrochureVo.ImageVo.UrlVo -> {
                AsyncImage(
                    model = item.image.value,
                    contentDescription = null,
                    modifier = Modifier.aspectRatio(IMAGE_ASPECT_RATIO)
                )
            }
        }

        Text(
            text = item.publisherName,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun BrochuresScreenPreview() {
    BrochuresScreen(
        state = HomeState.Success(
            brochures = (0..10).map { index ->
                BrochureVo(
                    id = index.toLong(),
                    type = if (index % 3 == 0) PREMIUM else USUAL,
                    image = if (index % 4 == 0) {
                        BrochureVo.ImageVo.PlaceholderVo
                    } else {
                        BrochureVo.ImageVo.UrlVo(
                            "https://content-media.bonial.biz/330dfb5c-b903-4fd4-9ae0-b196e0285e82/preview.jpg"
                        )
                    },
                    publisherName = "Publisher $index",
                    distance = 3.2
                )
            },
            listFilterType = NO_FILTER,
        ),
        onEvent = {}
    )
}