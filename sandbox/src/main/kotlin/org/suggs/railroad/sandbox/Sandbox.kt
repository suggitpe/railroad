package org.suggs.railroad.sandbox

import org.slf4j.LoggerFactory
import uk.co.nationalrail.opendbws.AccessToken
import uk.co.nationalrail.opendbws.GetBoardRequestParams
import uk.co.nationalrail.opendbws.Ldb
import uk.co.nationalrail.opendbws.StationBoard

class Sandbox {
    companion object {

        private val log = LoggerFactory.getLogger(this::class.java)

        fun printStationBoard(apiToken: String) {
            var board = getStationBoardFor("KET", accessTokenFrom(apiToken))
            val services = board.trainServices.service
            services.map{log.info("Train to ${it.destination.location[0].locationName} departs at ${it.std} and is ${it.etd}") }
            log.debug("")
        }

        private fun getStationBoardFor(station: String, token: AccessToken): StationBoard {
            log.info("Using token ${token.tokenValue}")
            val soapService = Ldb().ldbServiceSoap
            return soapService.getDepartureBoard(buildBoardParams(), token).getStationBoardResult
        }

        private fun buildBoardParams(): GetBoardRequestParams {
            var reqParams = GetBoardRequestParams()
            reqParams.crs = "KET"
            reqParams.numRows = 10
            return reqParams
        }

        private fun accessTokenFrom(token: String) = AccessToken().apply {
            tokenValue = token
        }
    }
}