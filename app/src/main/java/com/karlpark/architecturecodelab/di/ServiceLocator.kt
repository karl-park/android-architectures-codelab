package com.karlpark.architecturecodelab.di

object ServiceLocator {

    private val services = mutableMapOf<Class<*>, Any>()

    fun <T> registerService(serviceType: Class<T>, serviceInstance: T) {
        services[serviceType] = serviceInstance as Any
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getService(serviceType: Class<T>): T {
        return services[serviceType] as T?
            ?: throw IllegalStateException("Service not registered: ${serviceType.simpleName}")
    }
}