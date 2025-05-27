package io.marelso.schedule.domain

data class Device(
    val id: String? = null,
    val name: String? = null,
    val schedules: List<Schedule> = listOf(),
)
