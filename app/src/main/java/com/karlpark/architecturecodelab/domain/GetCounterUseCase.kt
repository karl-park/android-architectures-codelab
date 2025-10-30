package com.karlpark.architecturecodelab.domain

import com.karlpark.architecturecodelab.presentation.Screen

class GetCounterUseCase(
    private val repository: CounterRepository
) {
    operator fun invoke(screen: Screen) = repository.getCount(screen.ordinal)
}