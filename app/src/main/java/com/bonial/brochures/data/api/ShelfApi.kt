package com.bonial.brochures.data.api

import com.bonial.brochures.data.model.ShelfDto
import retrofit2.http.GET

interface ShelfApi {

    @GET("shelf.json")
    suspend fun getShelf(): ShelfDto
}