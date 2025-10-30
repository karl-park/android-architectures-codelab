package com.karlpark.architecturecodelab.presentation.mvp

import com.karlpark.architecturecodelab.domain.DecrementCounterUseCase
import com.karlpark.architecturecodelab.domain.GetCounterUseCase
import com.karlpark.architecturecodelab.domain.IncrementCounterUseCase
import com.karlpark.architecturecodelab.presentation.Screen

class CounterMvpPresenter(
    private val incrementUseCase: IncrementCounterUseCase,
    private val decrementUseCase: DecrementCounterUseCase,
    private val getCounterUseCase: GetCounterUseCase,
) : CounterMvpContract.Presenter {

    private val screen = Screen.MVP
    private var view: CounterMvpContract.View? = null
    private val currentCount: Int
        get() = getCounterUseCase.invoke(screen)

    override fun attach(view: CounterMvpContract.View) {
        this.view = view
        view.displayCount(currentCount)
    }
    override fun detach() { this.view = null }

    override fun onIncrementClicked() {
        incrementUseCase(screen)
        view?.displayCount(currentCount) // Direct View update
    }
    override fun onDecrementClicked() {
        decrementUseCase(screen)
        view?.displayCount(currentCount) // Direct View update
    }
}