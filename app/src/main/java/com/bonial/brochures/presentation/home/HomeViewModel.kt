package com.bonial.brochures.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonial.brochures.domain.interactor.ShelfInteractor
import com.bonial.brochures.domain.model.BrochureVo
import com.bonial.brochures.presentation.common.Dispatchers
import com.bonial.brochures.presentation.home.HomeEvent.*
import com.bonial.brochures.presentation.home.ListFilterType.*
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

    private companion object {
        private const val FIVE_KM = 5
    }

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

    private var allBrochures: List<BrochureVo> = emptyList()
    private var filteredBrochures: List<BrochureVo>? = null

    private var listFilterType: ListFilterType = NO_FILTER

    override fun onEvent(event: HomeEvent) {
        when (event) {
            LOAD_BUTTON_CLICK -> loadBrochures()
            RETRY_BUTTON_CLICK -> loadBrochures()
            SWITCH_FILTER_STATE_BUTTON_CLICK -> switchFilterState()
        }
    }

    private fun loadBrochures() {
        viewModelScope.launch(exceptionHandler) {
            state.value = HomeState.Loading

            allBrochures = withContext(dispatchers.IO) {
                shelfInteractor.getBrochures()
            }

            state.value = HomeState.Success(allBrochures, listFilterType)
        }
    }

    private fun switchFilterState() {
        listFilterType = when (listFilterType) {
            NO_FILTER -> CLOSER_THAN_5_KM
            CLOSER_THAN_5_KM -> NO_FILTER
        }

        state.value = when (listFilterType) {
            NO_FILTER -> HomeState.Success(allBrochures, listFilterType)
            CLOSER_THAN_5_KM -> HomeState.Success(
                brochures = filteredBrochures
                    ?: allBrochures
                        .filter { it.distance < FIVE_KM }
                        .also { filteredBrochures = it },
                listFilterType = listFilterType
            )
        }
    }
}