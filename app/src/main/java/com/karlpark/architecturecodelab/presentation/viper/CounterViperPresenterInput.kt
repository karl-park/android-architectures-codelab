package com.karlpark.architecturecodelab.presentation.viper

interface CounterViperPresenterInput {
    fun viewLoaded()
    fun incrementClicked()
    fun decrementClicked()
}