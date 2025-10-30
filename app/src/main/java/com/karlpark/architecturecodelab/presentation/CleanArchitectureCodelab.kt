package com.karlpark.architecturecodelab.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.karlpark.architecturecodelab.presentation.mvc.MvcCounterScreen
import com.karlpark.architecturecodelab.presentation.mvi.MviCounterScreen
import com.karlpark.architecturecodelab.presentation.mvp.MvpCounterScreen
import com.karlpark.architecturecodelab.presentation.mvvm.MvvmCounterScreen
import com.karlpark.architecturecodelab.presentation.viper.ViperCounterScreen

@Composable
fun CleanArchitectureCodelab(
    modifier: Modifier,
) {
    // Simple navigation to demonstrate all screens
    var currentScreenNumber by remember { mutableIntStateOf(Screen.MVVM.ordinal) }
    val screenCount = 5

    Column(modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { currentScreenNumber = maxOf(1, currentScreenNumber - 1) },
                    enabled = currentScreenNumber > 1
                ) {
                    Text("Previous")
                }
                Text(
                    "Architecture Demo ${currentScreenNumber}/${screenCount}",
                    fontWeight = FontWeight.Bold
                )
                Button(
                    onClick = { currentScreenNumber = minOf(screenCount, currentScreenNumber + 1) },
                    enabled = currentScreenNumber < screenCount
                ) {
                    Text("Next")
                }
            }
        }

        when (currentScreenNumber) {
            1 -> MvvmCounterScreen()
            2 -> MviCounterScreen()
            3 -> MvpCounterScreen()
            4 -> MvcCounterScreen()
            5 -> ViperCounterScreen()
        }
    }
}