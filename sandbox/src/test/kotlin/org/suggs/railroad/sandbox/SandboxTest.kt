package org.suggs.railroad.sandbox

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.suggs.railroad.sandbox.Sandbox.Companion.listTrainsArrivingInto
import org.suggs.railroad.sandbox.Sandbox.Companion.listTrainsDepartingFrom
import java.io.File

@DisplayName("Railroad sandbox")
class SandboxTest {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Test
    @Disabled("Requires a token to run this integration test")
    fun `retrieves the departing trains from a station`() {
        listTrainsDepartingFrom("KET").to("STP").usingApiToken(readToken()).map { log.info("$it") }
    }

    @Test
    @Disabled("Requires a token to run this integration test")
    fun `retrieves the arriving trains at a station`() {
        listTrainsArrivingInto("STP").from("KET").usingApiToken(readToken()).map { log.info("$it") }
    }

    fun readToken(): String =
        File(ClassLoader.getSystemResource("token.txt").file).readText()

}