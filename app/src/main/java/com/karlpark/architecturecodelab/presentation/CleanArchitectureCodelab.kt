package com.karlpark.architecturecodelab.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { currentScreenNumber = maxOf(0, currentScreenNumber - 1) },
                    enabled = currentScreenNumber > 0,
                    contentPadding = PaddingValues(4.dp)
                ) {
                    Text("Previous")
                }
                Text(
                    "Architecture Demo ${currentScreenNumber + 1}/${screenCount}",
                    fontWeight = FontWeight.Bold
                )
                Button(
                    onClick = { currentScreenNumber = minOf(screenCount, currentScreenNumber + 1) },
                    enabled = currentScreenNumber < screenCount - 1,
                    contentPadding = PaddingValues(4.dp)
                ) {
                    Text("Next")
                }
            }
        }

        when (currentScreenNumber) {
            Screen.MVVM.ordinal -> MvvmCounterScreen()
            Screen.MVI.ordinal -> MviCounterScreen()
            Screen.MVP.ordinal -> MvpCounterScreen()
            Screen.MVC.ordinal -> MvcCounterScreen()
            Screen.VIPER.ordinal -> ViperCounterScreen()
        }
    }
}