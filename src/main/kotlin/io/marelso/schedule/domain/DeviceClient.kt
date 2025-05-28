package io.marelso.schedule.domain

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

data class DeviceClient(
    val id: String,
    val client: SseEmitter
)
