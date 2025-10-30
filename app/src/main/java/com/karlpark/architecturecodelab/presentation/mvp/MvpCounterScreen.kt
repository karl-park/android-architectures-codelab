package com.karlpark.architecturecodelab.presentation.mvp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.karlpark.architecturecodelab.di.ServiceLocator
import com.karlpark.architecturecodelab.domain.CounterRepository
import com.karlpark.architecturecodelab.domain.DecrementCounterUseCase
import com.karlpark.architecturecodelab.domain.GetCounterUseCase
import com.karlpark.architecturecodelab.domain.IncrementCounterUseCase
import com.karlpark.architecturecodelab.presentation.CounterUI

@Composable
fun MvpCounterScreen() {
    // Instantiate Presenter and Use Cases (simulated injection)
    val repository = ServiceLocator.getService(CounterRepository::class.java)
    val incrementUC = remember { IncrementCounterUseCase(repository) }
    val decrementUC = remember { DecrementCounterUseCase(repository) }
    val getUC = remember { GetCounterUseCase(repository) }
    val presenter: CounterMvpContract.Presenter = remember {
        CounterMvpPresenter(incrementUC, decrementUC, getUC)
    }

    // 1. Local Mutable State to drive Compose UI
    val countState = remember { mutableIntStateOf(0) }

    // 2. View implementation for the Presenter to talk to
    val mvpView = remember {
        object : CounterMvpContract.View {
            override fun displayCount(count: Int) {
                countState.intValue = count // Update Compose state
            }
        }
    }

    // 3. Lifecycle handling: attach/detach the Presenter to the View
    DisposableEffect(Unit) {
        presenter.attach(mvpView)
        onDispose { presenter.detach() }
    }

    Column(Modifier.padding(16.dp)) {
        Text("MVP Architecture", style = MaterialTheme.typography.bodyLarge)
        CounterUI(
            count = countState.intValue, // Use local state
            onIncrement = presenter::onIncrementClicked, // Delegate event
            onDecrement = presenter::onDecrementClicked
        )
    }
}