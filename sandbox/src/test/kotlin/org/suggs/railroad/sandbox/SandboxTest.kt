package org.suggs.railroad.sandbox

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.suggs.railroad.sandbox.Sandbox.Companion.printStationBoard
import java.io.File

@DisplayName("Railroad sandbox")
class SandboxTest {

    @Test
    @Disabled("Requires a token to run this integration test")
    fun `retrieves the departure board for a station`() {
        printStationBoard(readToken())
    }

    fun readToken(): String =
        File(ClassLoader.getSystemResource("token.txt").file).readText()

}