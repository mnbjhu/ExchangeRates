package com.example.domain.exception

object TooManyRequestsException:
    Exception("You have made too many requests to 'openexchangerates.org'")