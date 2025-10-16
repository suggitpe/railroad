package org.suggs.railroad.sandbox.domain

import uk.co.nationalrail.opendbws.ArrayOfArrayOfCallingPoints
import uk.co.nationalrail.opendbws.ServiceItemWithCallingPoints

data class DepartingRailService(
    override val trainId: String,
    override val origin: String,
    override val destination: String,
    val schedDeparture: String?,
    val estDeparture: String?,
    override val length: Int?,
    override val platform: String,
    override val callingPoints: List<String>
) : RailService(trainId, origin, destination, length, platform, callingPoints) {

    constructor(service: ServiceItemWithCallingPoints) : this(
        service.serviceID,
        service.origin.location[0].crs,
        service.destination.location[0].locationName,
        service.std,
        service.etd,
        service.length,
        service.platform,
        getCallingPointsFrom(service.subsequentCallingPoints) as List<String>
    )

    companion object {
        fun getCallingPointsFrom(callingPoints: ArrayOfArrayOfCallingPoints?): List<String?>? {
            return callingPoints?.callingPointList[0]?.callingPoint?.map { it.crs }
        }
    }
}