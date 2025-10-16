package org.suggs.railroad.sandbox

import org.slf4j.LoggerFactory
import org.suggs.railroad.sandbox.domain.ArrivingRailService
import org.suggs.railroad.sandbox.domain.DepartingRailService
import uk.co.nationalrail.opendbws.AccessToken
import uk.co.nationalrail.opendbws.GetBoardRequestParams
import uk.co.nationalrail.opendbws.LDBServiceSoap
import uk.co.nationalrail.opendbws.Ldb
import uk.co.nationalrail.opendbws.StationBoardWithDetails

class Sandbox {
    companion object {

        private val log = LoggerFactory.getLogger(this::class.java)

        fun printStationBoard(apiToken: String) {

            val soapService = Ldb().ldbServiceSoap

            log.debug("Departures from ...")
            val depBoard = getStationDepartureBoardFor("KET", soapService, accessTokenFrom(apiToken))
            depBoard.trainServices.service.map { log.debug("${DepartingRailService(it)}") }
            val foo = DepartingRailService(depBoard.trainServices.service[0])

            log.debug("Arrivals to ...")
            val arrBoard = getStationArrivalBoardFor("KET", soapService, accessTokenFrom(apiToken))
            arrBoard.trainServices.service.map { log.debug("${ArrivingRailService(it)}") }
        }

        private fun getStationDepartureBoardFor(station: String, soapService: LDBServiceSoap, token: AccessToken): StationBoardWithDetails {
            return soapService.getDepBoardWithDetails(buildBoardParams(station), token).getStationBoardResult
        }

        private fun getStationArrivalBoardFor(
            station: String,
            soapService: LDBServiceSoap,
            accessTokenFrom: AccessToken
        ): StationBoardWithDetails {
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