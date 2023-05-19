package com.example.data.impl

import com.example.data.Client
import com.example.domain.exception.MissingOrInvalidAppIdException
import com.example.domain.exception.TooManyRequestsException
import com.example.util.appIdProperty
import com.example.util.config
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class OpenExchangeClientImpl: Client {
    override val client: HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        defaultRequest {
            url(baseUrl)
            header("Authorization", "Token $appId")
        }
        HttpResponseValidator {
            validateResponse { response ->
                when(response.status) {
                    HttpStatusCode.Unauthorized -> throw MissingOrInvalidAppIdException
                    HttpStatusCode.TooManyRequests -> throw TooManyRequestsException
                }
            }
        }
    }

    companion object {

        const val baseUrl = "https://openexchangerates.org/"

        @JvmStatic
        val appId = config[appIdProperty]
    }
}