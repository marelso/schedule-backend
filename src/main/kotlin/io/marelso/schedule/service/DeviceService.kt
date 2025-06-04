package io.marelso.schedule.service

import io.marelso.schedule.domain.Device
import io.marelso.schedule.domain.DeviceCreateDTO
import io.marelso.schedule.domain.ScheduleCreateDTO
import io.marelso.schedule.repository.DeviceRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@Service
class DeviceService(private val repository: DeviceRepository) {
    private val listenerService: ListenerService = ListenerService()

    fun subscribe(id: String): SseEmitter = listenerService.subscribe(id)

    fun create(device: DeviceCreateDTO): Device = repository.save(device.toDevice())

    fun delete(id: String) = repository.deleteById(id)

    fun getAll(pageable: Pageable): Page<Device> = repository.findAll(pageable)

    fun getById(id: String): Device = findById(id)

    private fun findById(id: String): Device = repository
        .findByIdOrNull(id) ?: throw RuntimeException("Device with id $id not found")

    fun addSchedule(id: String, schedule: ScheduleCreateDTO): Device {
        if(schedule.end.isBefore(schedule.start)) throw RuntimeException("End of schedule can't be before start")

        val device = findById(id)

        return repository.save(device.copy(schedules = device.schedules.plus(schedule.toSchedule()))).apply {
            listenerService.notify(id)
        }
    }

    fun removeSchedule(id: String, scheduleId: String): Device {
        val device = findById(id)

        return repository.save(device.copy(schedules = device.schedules.filterNot { it.id == scheduleId })).apply {
            listenerService.notify(id)
        }
    }
}