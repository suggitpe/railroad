package org.suggs.railroad.sandbox

import org.slf4j.LoggerFactory
import uk.co.nationalrail.opendbws.AccessToken
import uk.co.nationalrail.opendbws.Ldb

class Sandbox {
    companion object {

        private val log = LoggerFactory.getLogger(this::class.java)

        fun testConnectionWithToken(apiToken: String) {
            log.info("Using token $apiToken")
            val soapService = Ldb().ldbServiceSoap
            val token = AccessToken()
            token.tokenValue = apiToken

//            var board = soapService.getDepartureBoard()
        }
    }
}