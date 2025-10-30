package com.karlpark.architecturecodelab.presentation.mvc

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import com.karlpark.architecturecodelab.domain.DecrementCounterUseCase
import com.karlpark.architecturecodelab.domain.GetCounterUseCase
import com.karlpark.architecturecodelab.domain.IncrementCounterUseCase
import com.karlpark.architecturecodelab.presentation.Screen

class CounterController(
    private val incrementUseCase: IncrementCounterUseCase,
    private val decrementUseCase: DecrementCounterUseCase,
    getCounterUseCase: GetCounterUseCase,
) {

    private val screen = Screen.MVC
    // Controller holds the state (Model)
    private val _count = mutableIntStateOf(getCounterUseCase.invoke(screen))
    val count: State<Int> = _count // Observable state for Compose View

    fun handleIncrement() {
        _count.intValue = incrementUseCase(screen)
    }

    fun handleDecrement() {
        _count.intValue = decrementUseCase(screen)
    }
}