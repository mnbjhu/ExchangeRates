package com.example.data.impl

import com.example.data.Client
import com.example.data.ExchangeRatesRepo
import com.example.data.impl.OpenExchangeClientImpl.Companion.appId
import com.example.domain.model.Currency
import com.example.domain.model.LatestRatesResponse
import com.example.domain.exception.CurrencyNotFoundException
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ExchangeRatesRepoImpl: ExchangeRatesRepo, KoinComponent {

    private val client: HttpClient

    init {
        val instance by inject<Client>()
        client = instance.client
    }

    override suspend fun getCurrencies(): List<Currency> {
        val response = client.get("/api/currencies.json")
        return response.body<Map<String, String>>()
            .map { (shortName, fullName) -> Currency(shortName, fullName) }
    }

    override suspend fun convert(value: Double, from: String, to: String): Double {
        val response = client.get("/api/latest.json") {
            parameter("app_id", appId)
        }
        val latest = response.body<LatestRatesResponse>()
        val fromRate = latest.rates[from] ?: throw CurrencyNotFoundException(from)
        val toRate = latest.rates[to] ?: throw CurrencyNotFoundException(to)
        return toRate * value / fromRate
    }
}
