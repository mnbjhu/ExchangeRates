package com.example.util

import com.example.data.Client
import com.example.data.impl.OpenExchangeClientImpl
import com.example.data.ExchangeRatesRepo
import com.example.data.impl.ExchangeRatesRepoImpl
import org.koin.dsl.module

val appModule = module {
    single<Client> { OpenExchangeClientImpl() }
    single<ExchangeRatesRepo> { ExchangeRatesRepoImpl() }
}