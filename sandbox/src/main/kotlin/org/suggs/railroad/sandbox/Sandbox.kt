package org.suggs.railroad.sandbox

import org.slf4j.LoggerFactory
import uk.co.nationalrail.opendbws.AccessToken
import uk.co.nationalrail.opendbws.GetBoardRequestParams
import uk.co.nationalrail.opendbws.Ldb
import uk.co.nationalrail.opendbws.StationBoardWithDetails

class Sandbox {
    companion object {

        private val log = LoggerFactory.getLogger(this::class.java)

        fun printStationBoard(apiToken: String) {
            log.debug("Departures from ...")
            val depBoard = getStationDepartureBoardFor("KET", accessTokenFrom(apiToken))
            depBoard.trainServices.service.map { log.debug("${RailService(it)}") }

            log.debug("Arrivals to ...")
            val arrBoard = getStationArrivalBoardFor("KET", accessTokenFrom(apiToken))
            arrBoard.trainServices.service.map { log.debug("${RailService(it)}") }
        }

        private fun getStationDepartureBoardFor(station: String, token: AccessToken): StationBoardWithDetails {
            val soapService = Ldb().ldbServiceSoap
            return soapService.getDepBoardWithDetails(buildBoardParams(station), token).getStationBoardResult
        }

        private fun getStationArrivalBoardFor(station: String, accessTokenFrom: AccessToken): StationBoardWithDetails {
            val soapService = Ldb().ldbServiceSoap
            return soapService.getArrBoardWithDetails(buildBoardParams(station), accessTokenFrom).getStationBoardResult
        }

        private fun buildBoardParams(station: String): GetBoardRequestParams {
            var reqParams = GetBoardRequestParams()
            reqParams.crs = station
            reqParams.numRows = 10
            return reqParams
        }

        private fun accessTokenFrom(token: String) = AccessToken().apply {
            tokenValue = token
        }
    }
}