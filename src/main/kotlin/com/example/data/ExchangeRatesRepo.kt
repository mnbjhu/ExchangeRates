package com.example.data

import com.example.domain.model.Currency

interface ExchangeRatesRepo {
    suspend fun getCurrencies(): List<Currency>
    suspend fun convert(value: Double, from: String, to: String): Double
}