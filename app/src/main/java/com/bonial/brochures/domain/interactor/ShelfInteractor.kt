package com.bonial.brochures.domain.interactor

import com.bonial.brochures.data.repository.ShelfRepository
import com.bonial.brochures.domain.mapper.ShelfMapper
import com.bonial.brochures.domain.model.BrochureVo
import javax.inject.Inject

interface ShelfInteractor {
    suspend fun getBrochures(): List<BrochureVo>
}

class ShelfInteractorImpl @Inject constructor(
    private val shelfRepository: ShelfRepository,
    private val shelfMapper: ShelfMapper
) : ShelfInteractor {
    override suspend fun getBrochures(): List<BrochureVo> {
        val shelf = shelfRepository.getShelf()
        return shelfMapper.toBrochures(shelf)
    }
}