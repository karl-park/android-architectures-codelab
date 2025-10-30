package com.karlpark.architecturecodelab.presentation.mvc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.karlpark.architecturecodelab.di.ServiceLocator
import com.karlpark.architecturecodelab.presentation.CounterUI

@Composable
fun MvcCounterScreen() {
    val controller = remember {
        ServiceLocator.getService(CounterController::class.java)
    }
    val count by controller.count

    Column(Modifier.padding(16.dp)) {
        Text("MVC Architecture", style = MaterialTheme.typography.bodyLarge)
        CounterUI(
            count = count,
            onIncrement = controller::handleIncrement, // Delegate event
            onDecrement = controller::handleDecrement
        )
    }
}