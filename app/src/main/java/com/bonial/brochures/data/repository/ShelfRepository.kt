package com.bonial.brochures.data.repository

import com.bonial.brochures.data.api.ShelfApi
import com.bonial.brochures.data.model.ShelfDto
import javax.inject.Inject

interface ShelfRepository {
    suspend fun getShelf(): ShelfDto
}

class ShelfRepositoryImpl @Inject constructor(
    private val shelfApi: ShelfApi
) : ShelfRepository {

    override suspend fun getShelf(): ShelfDto {
        return shelfApi.getShelf()
    }
}