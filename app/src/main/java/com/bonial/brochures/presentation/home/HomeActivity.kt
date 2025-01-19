package com.bonial.brochures.presentation.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bonial.brochures.presentation.common.viewModels
import com.bonial.brochures.presentation.home.screen.HomeScreen
import com.bonial.brochures.presentation.common.ui.theme.BrochuresTheme

class HomeActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BrochuresTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    HomeScreen(
                        state = state,
                        onEvent = { event: HomeEvent ->
                            viewModel.onEvent(event)
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}