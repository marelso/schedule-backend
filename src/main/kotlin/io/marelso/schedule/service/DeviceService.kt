package io.marelso.schedule.service

import io.marelso.schedule.domain.Device
import io.marelso.schedule.domain.Schedule
import io.marelso.schedule.repository.DeviceRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class DeviceService(private val repository: DeviceRepository) {
    fun create(device: Device): Device = repository.save(device)

    fun delete(id: String) = repository.deleteById(id)

    fun getById(id: String) = findById(id)

    private fun findById(id: String): Device = repository
        .findByIdOrNull(id) ?: throw RuntimeException("Device with id $id not found")

    fun addSchedule(id: String, schedule: Schedule) = findById(id).apply {
        repository.save(copy(schedules = schedules.plus(schedule)))
    }

    fun removeSchedule(id: String, scheduleId: String) = findById(id).apply {
        repository.save(copy(schedules = schedules.filterNot { it.id == scheduleId }))
    }
}