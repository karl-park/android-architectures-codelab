package com.karlpark.architecturecodelab.presentation.mvp

interface CounterMvpContract {
    interface View {
        fun displayCount(count: Int) // Passive View: Presenter tells it exactly what to show
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun onIncrementClicked()
        fun onDecrementClicked()
    }
}