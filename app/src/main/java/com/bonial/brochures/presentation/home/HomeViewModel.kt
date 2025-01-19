package com.bonial.brochures.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonial.brochures.domain.interactor.ShelfInteractor
import com.bonial.brochures.presentation.common.Dispatchers
import com.bonial.brochures.presentation.home.HomeEvent.LOAD_BUTTON_CLICK
import com.bonial.brochures.presentation.home.HomeEvent.RETRY_BUTTON_CLICK
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface HomeViewModel {
    val state: StateFlow<HomeState>

    fun onEvent(event: HomeEvent)
}

class HomeViewModelImpl @Inject constructor(
    private val shelfInteractor: ShelfInteractor,
    private val dispatchers: Dispatchers
) : ViewModel(), HomeViewModel {
    override val state = MutableStateFlow<HomeState>(HomeState.Initial)

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()

        /**
         * Comment for reviewers
         *
         * Of course, in production applications, we must show detailed error descriptions,
         * but for simplicity in this test task, I just wrote that "something went wrong".
         */
        state.value = HomeState.Error("Smth went wrong (${exception.javaClass.simpleName})")
    }

    override fun onEvent(event: HomeEvent) {
        when (event) {
            LOAD_BUTTON_CLICK -> loadBrochures()
            RETRY_BUTTON_CLICK -> loadBrochures()
        }
    }

    private fun loadBrochures() {
        viewModelScope.launch(exceptionHandler) {
            state.value = HomeState.Loading

            val brochures = withContext(dispatchers.IO) {
                shelfInteractor.getBrochures()
            }

            state.value = HomeState.Success(brochures)
        }
    }
}