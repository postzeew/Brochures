package com.bonial.brochures.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonial.brochures.domain.interactor.ShelfInteractor
import com.bonial.brochures.presentation.common.Dispatchers
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface HomeViewModel {
    fun onLoadButtonClicked()
}

class HomeViewModelImpl @Inject constructor(
    private val shelfInteractor: ShelfInteractor,
    private val dispatchers: Dispatchers
) : ViewModel(), HomeViewModel {
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        // TODO
    }

    override fun onLoadButtonClicked() {
        viewModelScope.launch(exceptionHandler) {
            val brochures = withContext(dispatchers.IO) {
                shelfInteractor.getBrochures()
            }

            brochures.forEach { brochure ->
                Log.d("HomeViewModel", "$brochure")
            }
        }
    }
}