package com.example.domain.exception

data class CurrencyNotFoundException(val currencyShortName: String):
    Exception("The currency '$currencyShortName' was not found.")
