package io.marelso.schedule.domain

data class DeviceCreateDTO(
    val id: String? = null,
    val name: String? = null,
    val schedules: List<ScheduleCreateDTO> = listOf(),
) {
    fun toDevice(): Device = Device(
        id = id,
        name = name,
        schedules = schedules.map { it.toSchedule() }
    )
}
