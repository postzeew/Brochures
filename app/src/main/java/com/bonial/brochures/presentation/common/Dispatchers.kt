package com.bonial.brochures.presentation.common

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers as BuiltInDispatchers

interface Dispatchers {
    val IO: CoroutineDispatcher
}

@Singleton
class DispatchersImpl @Inject constructor() : Dispatchers {
    override val IO: CoroutineDispatcher = BuiltInDispatchers.IO
}