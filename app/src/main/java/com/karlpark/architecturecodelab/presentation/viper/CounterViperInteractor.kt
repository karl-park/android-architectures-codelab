package com.karlpark.architecturecodelab.presentation.viper

import com.karlpark.architecturecodelab.domain.DecrementCounterUseCase
import com.karlpark.architecturecodelab.domain.GetCounterUseCase
import com.karlpark.architecturecodelab.domain.IncrementCounterUseCase
import com.karlpark.architecturecodelab.presentation.Screen

class CounterViperInteractor(
    private val incrementUseCase: IncrementCounterUseCase,
    private val decrementUseCase: DecrementCounterUseCase,
    private val getCounterUseCase: GetCounterUseCase,
) {
    private val screen = Screen.VIPER

    fun requestNewCount(isIncrement: Boolean): Int {
        return if (isIncrement) {
            incrementUseCase(screen)
        } else {
            decrementUseCase(screen)
        }
    }

    fun getCount(): Int = getCounterUseCase.invoke(screen)
}