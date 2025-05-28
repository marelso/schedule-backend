package io.marelso.schedule.service

import io.marelso.schedule.domain.Device
import io.marelso.schedule.domain.DeviceCreateDTO
import io.marelso.schedule.domain.ScheduleCreateDTO
import io.marelso.schedule.repository.DeviceRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class DeviceService(private val repository: DeviceRepository) {
    fun create(device: DeviceCreateDTO): Device = repository.existsById(device.id.orEmpty()).takeIf { it.not() }?.let {
        repository.save(device.toDevice())
    } ?: throw RuntimeException("Device ${device.name} already exists")

    fun delete(id: String) = repository.deleteById(id)

    fun getById(id: String): Device = findById(id)

    private fun findById(id: String): Device = repository
        .findByIdOrNull(id) ?: throw RuntimeException("Device with id $id not found")

    fun addSchedule(id: String, schedule: ScheduleCreateDTO): Device {
        val device = findById(id)

        return repository.save(
            device.copy(schedules = device.schedules.plus(schedule.toSchedule()))
        )
    }

    fun removeSchedule(id: String, scheduleId: String): Device {
        val device = findById(id)

        return repository.save(
            device.copy(schedules = device.schedules.filterNot { it.id == scheduleId })
        )
    }
}