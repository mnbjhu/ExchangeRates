package com.example.domain.exception

object MissingOrInvalidAppIdException:
    Exception("The openexchangerates.org is unauthorized, please ensure you have specified a valid 'app_id' in 'local.properties'")