package org.suggs.railroad.sandbox

import uk.co.nationalrail.opendbws.ServiceItemWithCallingPoints

data class RailService(
    val trainId: String,
    val origin: String,
    val destination: String,
    val std: String?,
    val etd: String?,
    val sta: String?,
    val eta: String?
) {
    constructor(service: ServiceItemWithCallingPoints) : this(
        service.serviceID,
        service.origin.location[0].locationName,
        service.destination.location[0].locationName,
        service.std,
        service.etd,
        service.sta,
        service.eta
    )
}
