package io.marelso.schedule.domain

import java.util.*

class Schedule(
    val id: String = UUID.randomUUID().toString(),
    val start: String? = null,
    val end: String? = null,
)