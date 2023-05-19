package com.example.mock

import com.example.data.ExchangeRatesRepo
import com.example.domain.exception.CurrencyNotFoundException
import com.example.domain.model.Currency

/**
 * Exchange rates repo test impl
 *
 * I was planning on using this to tests the /app route however I realised it might be out of scope of the assignment
 * @constructor Create empty Exchange rates repo test impl
 */

class ExchangeRatesRepoTestImpl: ExchangeRatesRepo {
    override suspend fun getCurrencies() = listOf(
        Currency("MK1", "My Mock Currency #1"),
        Currency("MK2", "My Mock Currency #2")
    )

    override suspend fun convert(value: Double, from: String, to: String): Double {
        return when {
            from == to && from in listOf("MK1", "MK2") -> value
            from == "MK1" && to == "MK2" -> 2 * value
            from == "MK2" && to == "MK1" -> 0.5 * value
            else -> throw CurrencyNotFoundException(listOf(from, to).first { it !in testCurrencies })
        }
    }
    companion object {
        @JvmStatic
        val testCurrencies = listOf("MK1", "MK2")
    }
}