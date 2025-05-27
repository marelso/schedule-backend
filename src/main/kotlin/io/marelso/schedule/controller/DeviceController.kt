package io.marelso.schedule.controller

import io.marelso.schedule.domain.Device
import io.marelso.schedule.service.DeviceService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/device")
class DeviceController(private val service: DeviceService) {

    @PostMapping
    fun create(@RequestBody device: Device): Device = service.create(device)

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: String) = service.findById(id)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable("id") id: String) = service.delete(id)

}