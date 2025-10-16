package org.suggs.railroad.sandbox

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.suggs.railroad.sandbox.Sandbox.Companion.listTrainsArrivingTo
import org.suggs.railroad.sandbox.Sandbox.Companion.listTrainsDepartingFrom
import java.io.File

@DisplayName("Railroad sandbox")
class SandboxTest {

    @Test
    @Disabled("Requires a token to run this integration test")
    fun `retrieves the departing trains from a station`() {
        listTrainsDepartingFrom("KET").usingApiToken(readToken())
    }

    @Test
    @Disabled("Requires a token to run this integration test")
    fun `retrieves the arriveing trains at a station`() {
        listTrainsArrivingTo("KET").usingApiToken(readToken())
    }

    fun readToken(): String =
        File(ClassLoader.getSystemResource("token.txt").file).readText()

}