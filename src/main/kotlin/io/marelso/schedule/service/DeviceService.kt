package io.marelso.schedule.service

import io.marelso.schedule.domain.Device
import io.marelso.schedule.domain.DeviceCreateDTO
import io.marelso.schedule.domain.ScheduleCreateDTO
import io.marelso.schedule.repository.DeviceRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class DeviceService(private val repository: DeviceRepository) {
    fun create(device: DeviceCreateDTO): Device = repository.save(device.toDevice())

    fun delete(id: String) = repository.deleteById(id)

    fun getById(id: String) = findById(id)

    private fun findById(id: String): Device = repository
        .findByIdOrNull(id) ?: throw RuntimeException("Device with id $id not found")

    fun addSchedule(id: String, schedule: ScheduleCreateDTO) = findById(id).apply {
        repository.save(copy(schedules = schedules.plus(schedule.toSchedule())))
    }

    fun removeSchedule(id: String, scheduleId: String) = findById(id).apply {
        repository.save(copy(schedules = schedules.filterNot { it.id == scheduleId }))
    }
}