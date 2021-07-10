package com.example.teacher.event

import com.example.teacher.domain.Task
import com.example.teacher.domain.enum.TaskType
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.example.teacher.dto.NotificationDTO
import com.example.teacher.dto.TaskDTO
import com.example.teacher.repository.TaskRepository
import com.example.teacher.utils.Const
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*


@Component
class Producer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val taskRepository: TaskRepository,
    ) {
    private val logger = LoggerFactory.getLogger(Producer::class.java)

    fun notifyStudent(notificationDTO: NotificationDTO) {
        val json = jacksonObjectMapper().writeValueAsString(notificationDTO)
        logger.info(String.format("Sending document to kafka (student)"))
        kafkaTemplate.send(Const().TOPIC_NOTIFICATION_STUDENT_SEND, json)
    }

    @Scheduled(fixedDelay = 100000, initialDelay = 10000)
    fun notifyAnalyze() {
        val tasks = taskRepository.getAllTaskByIsAnalyzed(Date())
        tasks.forEach { it ->
            val newTask = Task(it.id, it.title, it.description, it.dateFinalSubmit, it.documents, TaskType.IN_ANALYSE)
            taskRepository.save(newTask);
            val newTaskDTO = TaskDTO(newTask.id, it.title, newTask.description, newTask.dateFinalSubmit, newTask.documents, newTask.status)
            logger.info(it.toString());
            val json = jacksonObjectMapper().writeValueAsString(newTaskDTO)
            logger.info(String.format("Sending task to kafka"))
            kafkaTemplate.send(Const().TASK_ANALYZER_NOTIFICATION_SEND, json)
        }
    }
}
