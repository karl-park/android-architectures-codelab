package com.karlpark.architecturecodelab.presentation.viper

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
fun ViperCounterScreen() {
    val repository = ServiceLocator.getService(CounterRepository::class.java)
    val interactor = remember {
        CounterViperInteractor(
            IncrementCounterUseCase(repository),
            DecrementCounterUseCase(repository),
            GetCounterUseCase(repository)
        )
    }

    val countState = remember { mutableIntStateOf(interactor.getCount()) }
    val viewOutput = remember {
        object : CounterViperPresenterOutput {
            override fun displayCount(count: Int) {
                countState.intValue = count
            }
        }
    }

    val mockRouter = remember { Any() }

    val presenterInput: CounterViperPresenterInput = remember {
        CounterViperPresenter(interactor, mockRouter, viewOutput)
    }

    LaunchedEffect(Unit) {
        presenterInput.viewLoaded()
    }

    Column(Modifier.padding(16.dp)) {
        Text("VIPER Architecture", style = MaterialTheme.typography.bodyLarge)
        CounterUI(
            count = countState.intValue,
            onIncrement = presenterInput::incrementClicked,
            onDecrement = presenterInput::decrementClicked
        )
    }
}