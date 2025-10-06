package org.suggs.railroad.sandbox

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.suggs.railroad.sandbox.Sandbox.Companion.testConnectionWithToken
import java.io.File

@DisplayName("Railroad sandbox")
class SandboxTest {

    @Test
    fun `Connects to OpenLDBWS with a token`(){
        testConnectionWithToken(readToken())
    }

    fun readToken(): String =
        File(ClassLoader.getSystemResource("token.txt").file).readText()

}