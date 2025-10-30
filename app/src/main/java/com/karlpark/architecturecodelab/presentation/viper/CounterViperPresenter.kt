package com.karlpark.architecturecodelab.presentation.viper

class CounterViperPresenter(
    private val interactor: CounterViperInteractor,
    private val router: Any,
    private val view: CounterViperPresenterOutput
) : CounterViperPresenterInput {

    private var currentCount = interactor.getCount()

    override fun viewLoaded() {
        view.displayCount(currentCount)
    }
    override fun incrementClicked() {
        currentCount = interactor.requestNewCount(true)
        view.displayCount(currentCount)
    }
    override fun decrementClicked() {
        currentCount = interactor.requestNewCount(false)
        view.displayCount(currentCount)
    }
}