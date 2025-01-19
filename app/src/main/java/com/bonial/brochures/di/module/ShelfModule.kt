package com.bonial.brochures.di.module

import com.bonial.brochures.data.repository.ShelfRepository
import com.bonial.brochures.data.repository.ShelfRepositoryImpl
import com.bonial.brochures.domain.interactor.ShelfInteractor
import com.bonial.brochures.domain.interactor.ShelfInteractorImpl
import com.bonial.brochures.domain.mapper.ShelfMapper
import com.bonial.brochures.domain.mapper.ShelfMapperImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ShelfModule {

    @Binds
    @Singleton
    fun bindShelfRepository(impl: ShelfRepositoryImpl): ShelfRepository

    @Binds
    @Singleton
    fun bindShelfInteractor(impl: ShelfInteractorImpl): ShelfInteractor

    @Binds
    @Singleton
    fun bindShelfMapper(impl: ShelfMapperImpl): ShelfMapper
}