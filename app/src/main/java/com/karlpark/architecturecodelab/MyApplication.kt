package com.karlpark.architecturecodelab

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.karlpark.architecturecodelab.data.CounterRepositoryImpl
import com.karlpark.architecturecodelab.di.ServiceLocator
import com.karlpark.architecturecodelab.domain.CounterRepository
import com.karlpark.architecturecodelab.domain.DecrementCounterUseCase
import com.karlpark.architecturecodelab.domain.GetCounterUseCase
import com.karlpark.architecturecodelab.domain.IncrementCounterUseCase
import com.karlpark.architecturecodelab.presentation.mvi.CounterMVIProcessor
import com.karlpark.architecturecodelab.presentation.mvvm.CounterMVVMViewModel

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        ServiceLocator.registerService(
            serviceType = CounterRepository::class.java,
            serviceInstance = CounterRepositoryImpl(),
        )

        ServiceLocator.registerService(
            serviceType = ViewModelProvider.Factory::class.java,
            serviceInstance = object : ViewModelProvider.Factory {
                private val repo = ServiceLocator.getService(CounterRepository::class.java)
                private val incrementUseCase = IncrementCounterUseCase(repo)
                private val decrementUseCase = DecrementCounterUseCase(repo)

                private val getCounterUseCase = GetCounterUseCase(repo)

                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return when {
                        modelClass.isAssignableFrom(CounterMVVMViewModel::class.java) -> {
                            CounterMVVMViewModel(incrementUseCase, decrementUseCase, getCounterUseCase) as T
                        }

                        modelClass.isAssignableFrom(CounterMVIProcessor::class.java) -> {
                            CounterMVIProcessor(incrementUseCase, decrementUseCase, getCounterUseCase) as T
                        }
                        // MVC Controller and MVP/VIPER Presenter are not ViewModels, handled separately.
                        else -> throw IllegalArgumentException("Unknown ViewModel class")
                    }
                }
            }
        )
    }
}