package com.example.teacher.event

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.example.teacher.dto.NotificationDTO
import com.example.teacher.utils.Const
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class Producer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
) {
    private val logger = LoggerFactory.getLogger(Producer::class.java)

    fun notifyStudent(notificationDTO: NotificationDTO) {
        val json = jacksonObjectMapper().writeValueAsString(notificationDTO)
        logger.info(String.format("Sending document to kafka"))
        kafkaTemplate.send(Const().TOPIC_NOTIFICATION_STUDENT_SEND, json)
    }
}
