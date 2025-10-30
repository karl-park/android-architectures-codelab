package com.karlpark.architecturecodelab.domain

import com.karlpark.architecturecodelab.presentation.Screen

class DecrementCounterUseCase(
    private val repository: CounterRepository
) {
    operator fun invoke(screen: Screen) = repository.updateCount(screen.ordinal, false)
}