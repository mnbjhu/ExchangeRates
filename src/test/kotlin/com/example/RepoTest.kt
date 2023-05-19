package com.example

import com.example.data.ExchangeRatesRepo
import com.example.domain.model.Currency
import com.example.domain.exception.CurrencyNotFoundException
import com.example.util.appModule
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be in range`
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.`should contain all`
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class RepoTest: KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(appModule)
    }

    private val repo by inject<ExchangeRatesRepo>()

    @Test
    fun `The exchange rates repo can retrieve a list of all currencies`() {
        val retrieved = runBlocking {
            repo.getCurrencies()
        }
        retrieved `should contain all` listOf(
            Currency("GBP", "British Pound Sterling"),
            Currency("EUR", "Euro"),
            Currency("USD", "United States Dollar"),
        )
    }

    @Test
    fun `The exchange rates repo can convert a valid currency to another valid currency`() {
        val converted = runBlocking {
            repo.convert(1.0, "GBP", "USD")
        }
        converted `should be in range` 1.0..1.5
    }

    @Test
    fun `The exchange rates repo should throw a CurrencyNotFoundException when the 'from' currency doesn't exist`() {
        runBlocking {
            val result = runCatching {
                repo.convert(1.0, "FAKE", "USD")
            }
            assert(result.isFailure)
            val exception = result.exceptionOrNull()
            exception `should be instance of` CurrencyNotFoundException::class
            exception!!.message `should be equal to` "The currency 'FAKE' was not found."
        }
    }

    @Test
    fun `The exchange rates repo should throw a CurrencyNotFoundException when the 'to' currency doesn't exist`() {
        runBlocking {
            val result = runCatching {
                repo.convert(1.0, "USD", "FAKE")
            }
            assert(result.isFailure)
            val exception = result.exceptionOrNull()
            exception `should be instance of` CurrencyNotFoundException::class
            exception!!.message `should be equal to` "The currency 'FAKE' was not found."
        }
    }
}