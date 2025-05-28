package io.marelso.schedule.repository

import io.marelso.schedule.domain.Device
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface DeviceRepository: MongoRepository<Device, String> {
    fun existsByName(name: String): Boolean
}