package io.marelso.schedule.domain

import java.time.OffsetDateTime

data class ScheduleCreateDTO(
    val start: OffsetDateTime,
    val end: OffsetDateTime
) {
    fun toSchedule() = Schedule(
        start = start.toString(),
        end = end.toString()
    )
}
