package com.karlpark.architecturecodelab.presentation.mvi

sealed class CounterIntent {
    object Increment : CounterIntent()
    object Decrement : CounterIntent()
}