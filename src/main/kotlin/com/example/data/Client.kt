package com.example.data

import io.ktor.client.*


interface Client {
    val client: HttpClient
}