package com.aksio.core.tests_shared.fake.common

import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

class TestClock(
    private var instant: Instant,
    private val zone: ZoneId
) : Clock() {

    @Inject
    constructor() : this (
        systemDefaultZone().instant(),
        ZoneId.of("Europe/Kiev")
    )

    override fun instant(): Instant = instant

    override fun getZone(): ZoneId = zone

    override fun withZone(zone: ZoneId): Clock =
        if (zone == this.zone) this else TestClock(instant, zone)

    override fun toString(): String {
        return "FixedInstant[$instant,$zone]"
    }

    fun setLocalDateTime(localDateTime: LocalDateTime) {
        instant = localDateTime.atZone(zone).toInstant()
    }

}