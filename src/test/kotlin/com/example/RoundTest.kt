package com.example

import com.example.domain.roundTwoDp
import org.amshove.kluent.`should be equal to`
import org.junit.Test

class RoundTest {

    @Test
    fun `Basic Test`() {
        roundTwoDp(1.234567) `should be equal to` "1.23"
        roundTwoDp(Math.PI) `should be equal to` "3.14"
        roundTwoDp(0.999) `should be equal to` "1.00"
    }

    @Test
    fun `Padding Test`() {
        roundTwoDp(0.001) `should be equal to` "0.00"
        roundTwoDp(0.01) `should be equal to` "0.01"
        roundTwoDp(0.1) `should be equal to` "0.10"
        roundTwoDp(1.0) `should be equal to` "1.00"
    }
}