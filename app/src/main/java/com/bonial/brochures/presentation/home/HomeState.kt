package com.bonial.brochures.presentation.home

import com.bonial.brochures.domain.model.BrochureVo

sealed interface HomeState {
    data object Initial : HomeState
    data object Loading : HomeState
    data class Success(val brochures: List<BrochureVo>) : HomeState
    data class Error(val message: String) : HomeState
}