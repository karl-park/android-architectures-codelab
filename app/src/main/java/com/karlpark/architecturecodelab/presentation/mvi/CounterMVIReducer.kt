package com.karlpark.architecturecodelab.presentation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karlpark.architecturecodelab.domain.CounterState
import com.karlpark.architecturecodelab.domain.DecrementCounterUseCase
import com.karlpark.architecturecodelab.domain.GetCounterUseCase
import com.karlpark.architecturecodelab.domain.IncrementCounterUseCase
import com.karlpark.architecturecodelab.presentation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CounterMVIReducer(
    private val incrementUseCase: IncrementCounterUseCase,
    private val decrementUseCase: DecrementCounterUseCase,
    getCounterUseCase: GetCounterUseCase,
) : ViewModel() {

    private val screen = Screen.MVI
    private val _state = MutableStateFlow(CounterState(getCounterUseCase.invoke(screen)))
    val state: StateFlow<CounterState> = _state.asStateFlow()

    fun processIntent(intent: CounterIntent) {
        viewModelScope.launch {
            val currentState = _state.value
            val newCount = when (intent) {
                is CounterIntent.Increment -> incrementUseCase(screen)
                is CounterIntent.Decrement -> decrementUseCase(screen)
            }
            _state.value = currentState.copy(count = newCount)
        }
    }
}