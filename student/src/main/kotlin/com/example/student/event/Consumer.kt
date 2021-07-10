package com.example.student.event

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.example.student.dto.NotificationDTO
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class Consumer {
    private val logger = LoggerFactory.getLogger(Consumer::class.java)

    @KafkaListener(topics = ["notification_student"])
    fun notification(@Payload payload: String) {
        val notificationDTO:NotificationDTO = jacksonObjectMapper().readValue(payload)
        logger.info("Notification => {}", notificationDTO)
    }
}