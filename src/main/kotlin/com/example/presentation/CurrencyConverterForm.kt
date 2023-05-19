package com.example.presentation

import com.example.data.ExchangeRatesRepo
import com.example.domain.exception.CurrencyNotFoundException
import com.example.domain.model.Currency
import com.example.domain.roundTwoDp
import io.ktor.server.application.*
import io.ktor.util.pipeline.*
import kotlinx.html.*

fun BODY.conversionCalculatorForm(currencies: List<Currency>) {
    getForm {
        action = "/app"
        +"Enter An Amount:"
        textInput {
            required = true
            type = InputType.number
            name = "value"
        }
        +"Convert From:"
        select {
            required = true
            name = "from"
            currencies.forEach {
                option {
                    value = it.shortName
                    +"${it.fullName} (${it.shortName})"
                }
            }
        }
        +"Convert To:"
        select {
            required = true
            name = "to"
            currencies.forEach {
                option {
                    value = it.shortName
                    +"${it.fullName} (${it.shortName})"
                }

            }
        }
        input {
            type = InputType.submit
            value = "Convert"
        }
    }
}

typealias HttpRequestScope = PipelineContext<Unit, ApplicationCall>
suspend fun HttpRequestScope.getDisplayedMessage(repo: ExchangeRatesRepo): String {

    val value = call.parameters["value"]
    val from = call.parameters["from"]
    val to = call.parameters["to"]

    if(value == null || from == null || to == null) return "Please make sure all fields have been completed."
    return try {
        val converted = repo.convert(value.toDouble(), from, to)
        "$value$from in $to is ${roundTwoDp(converted)}$to."
    }
    catch (e: NumberFormatException) { "The amount should be a number." }
    catch (e: CurrencyNotFoundException) { "The currency you selected couldn't be found: '${e.currencyShortName}'." }
    catch (e: Exception) { "Something went wrong. Please contact an admin or see logs for details." }
}
