package com.example.plugins

import com.example.util.appModule
import org.koin.core.context.startKoin
import org.koin.logger.slf4jLogger

fun configureKoin() {
    startKoin {
        slf4jLogger()
        modules(appModule)
    }
}