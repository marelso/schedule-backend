package io.marelso.schedule.service

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

class ListenerService {
    private val listeners = mutableMapOf<String, MutableList<SseEmitter>>()

    fun subscribe(deviceId: String): SseEmitter = SseEmitter(0L).apply {
        addEmitter(deviceId, this)

        onCompletion { removeEmitter(deviceId, this) }
        onTimeout { complete() }
    }

    fun notify(deviceId: String) {
        listeners[deviceId]?.forEach { client ->
            client.send(Unit)
        }
    }

    private fun removeEmitter(deviceId: String, emitter: SseEmitter) {
        listeners[deviceId]?.apply {
            remove(emitter)
        }
    }

    private fun addEmitter(deviceId: String, emitter: SseEmitter) {
        listeners.putIfAbsent(deviceId, mutableListOf())

        listeners[deviceId]?.add(emitter)
    }
}