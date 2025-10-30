package com.karlpark.architecturecodelab.presentation.mvp

interface CounterMvpContract {
    interface View {
        fun displayCount(count: Int)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun onIncrementClicked()
        fun onDecrementClicked()
    }
}