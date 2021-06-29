package com.example.student.event

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.example.student.domain.Task
import com.example.student.dto.NotificationDTO
import com.example.student.dto.TaskDTO
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class Consumer {
    private val logger = LoggerFactory.getLogger(Consumer::class.java)

    @KafkaListener(topics = ["registered_task"], groupId = "group_id")
    fun consume(taskDTO:TaskDTO){
        try {
            val task = Task(
                taskDTO.id,
                taskDTO.title,
                taskDTO.description,
                taskDTO.dateFinalSubmit
            )

            logger.info("New task registered => ", task)
        } catch (e:Exception){
            logger.error("There was a problem registering a task", e.message)
        }
    }

    @KafkaListener(groupId = "groupId", topics = ["notification_student"])
    fun notification(@Payload payload: String) {
        val notificationDTO:NotificationDTO = jacksonObjectMapper().readValue(payload)
        logger.info("Notification => {}", notificationDTO)
    }
}