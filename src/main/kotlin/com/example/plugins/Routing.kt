package com.example.plugins

import com.example.data.ExchangeRatesRepo
import com.example.presentation.conversionCalculatorForm
import com.example.presentation.getDisplayedMessage
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.get
import kotlinx.html.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val repo by inject<ExchangeRatesRepo>()

    routing {

        staticResources("/static", "static")

        get("/app") {
            val currencies = repo.getCurrencies()
            val message = getDisplayedMessage(repo)
            call.respondHtml {
                head {
                    link {
                        rel = "stylesheet"
                        href = "/static/style.css"
                    }
                }
                body {
                    conversionCalculatorForm(currencies)
                    +message
                }
            }
        }

    }
}

