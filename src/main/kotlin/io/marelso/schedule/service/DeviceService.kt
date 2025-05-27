package io.marelso.schedule.service

import io.marelso.schedule.domain.Device
import io.marelso.schedule.repository.DeviceRepository
import org.springframework.stereotype.Service

@Service
class DeviceService(private val repository: DeviceRepository) {
    fun create(device: Device): Device = repository.save(device)

    fun delete(id: String) = repository.deleteById(id)

    fun findById(id: String) = repository.findById(id)
}