package io.marelso.schedule.controller

import io.marelso.schedule.domain.Device
import io.marelso.schedule.domain.DeviceCreateDTO
import io.marelso.schedule.domain.Schedule
import io.marelso.schedule.domain.ScheduleCreateDTO
import io.marelso.schedule.service.DeviceService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ResponseStatus

@RestController
@RequestMapping("/api/v1/device")
class DeviceController(private val service: DeviceService) {

    @PostMapping
    fun create(@RequestBody device: DeviceCreateDTO): ResponseEntity<Device> {
        return try {
            ResponseEntity.ok(service.create(device))
        } catch (e: RuntimeException) {
            ResponseEntity.status(HttpStatus.FOUND).build()
        }
    }

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: String) = ResponseEntity.ok(service.getById(id))

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable("id") id: String) = ResponseEntity.ok(service.delete(id))

    @PutMapping("/{id}/schedule")
    fun addSchedule(
        @PathVariable("id") id: String,
        @RequestBody schedule: ScheduleCreateDTO,
    ) = ResponseEntity.ok(service.addSchedule(id = id, schedule =  schedule))

    @PutMapping("/{id}/schedule/{scheduleId}")
    fun removeSchedule(
        @PathVariable("id") id: String,
        @PathVariable scheduleId: String
    ) = ResponseEntity.ok(service.removeSchedule(id = id, scheduleId =  scheduleId))
}