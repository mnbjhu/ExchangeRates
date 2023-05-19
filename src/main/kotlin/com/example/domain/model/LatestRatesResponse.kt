package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LatestRatesResponse(
    val base: String,
    val rates: Map<String, Double>
)