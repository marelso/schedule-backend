package io.marelso.schedule.domain

data class ScheduleCreateDTO(
    val start: String? = null,
    val end: String? = null
) {
    fun toSchedule() = Schedule(
        start = start,
        end = end
    )
}
