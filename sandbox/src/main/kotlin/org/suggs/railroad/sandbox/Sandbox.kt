package org.suggs.railroad.sandbox

import org.slf4j.LoggerFactory
import org.suggs.railroad.sandbox.domain.ArrivingRailService
import org.suggs.railroad.sandbox.domain.DepartingRailService
import org.suggs.railroad.sandbox.domain.RailService
import uk.co.nationalrail.opendbws.*

class Sandbox {

    interface TokenAuthenticated{
        fun usingApiToken(apiToken: String): List<RailService>
    }

    companion object {

        private val log = LoggerFactory.getLogger(this::class.java)
        private val soapService = Ldb().ldbServiceSoap

        fun listTrainsDepartingFrom(station: String): TokenAuthenticated {
            return object: TokenAuthenticated {
                override fun usingApiToken(apiToken: String): List<RailService> {
                    val depBoard = getStationDepartureBoardFor(station, soapService, accessTokenFrom(apiToken))
                    return depBoard.trainServices.service.map { DepartingRailService(it) }
                }
            }
        }

        fun listTrainsArrivingTo(station: String): TokenAuthenticated {
            return object: TokenAuthenticated {
                override fun usingApiToken(apiToken: String): List<RailService> {
                    val arrBoard = getStationArrivalBoardFor(station, soapService, accessTokenFrom(apiToken))
                    return arrBoard.trainServices.service.map { ArrivingRailService(it) }
                }
            }
        }

        private fun getStationDepartureBoardFor(
            station: String,
            soapService: LDBServiceSoap,
            token: AccessToken
        ): StationBoardWithDetails {
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