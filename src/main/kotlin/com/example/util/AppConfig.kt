package com.example.util

import com.natpryce.konfig.ConfigurationProperties
import com.natpryce.konfig.Key
import com.natpryce.konfig.stringType

val config = ConfigurationProperties.fromResource("local.properties")

val appIdProperty = Key("app_id", stringType)