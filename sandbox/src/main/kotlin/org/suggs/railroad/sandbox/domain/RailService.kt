package org.suggs.railroad.sandbox.domain

abstract class RailService(
    open val trainId: String,
    open val origin: String,
    open val destination: String,
    open val length: Int?,
    open val platform: String?,
    open val callingPoints: List<String>
)