package com.example.teacher.event

import com.example.teacher.domain.Document
import com.example.teacher.domain.Task
import com.example.teacher.dto.NotificationDTO
import com.example.teacher.dto.ResultDTO
import com.example.teacher.dto.SubmitDocumentDTO
import com.example.teacher.repository.DocumentRepository
import com.example.teacher.repository.TaskRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.hibernate.exception.ConstraintViolationException
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class Consumer(
    private val taskRepository: TaskRepository,
    private val documentRepository: DocumentRepository,
    private val producer: Producer
) {
    private val logger = LoggerFactory.getLogger(Consumer::class.java)

    @KafkaListener(topics = ["submitted_document"])
    fun submittedDocument(@Payload payload: String){
        try {
            val submitDocumentDTO:SubmitDocumentDTO = jacksonObjectMapper().readValue(payload)
            val documentDTO = submitDocumentDTO.documentDTO
            val task: Task? = taskRepository.findByIdOrNull(submitDocumentDTO.idTask)

            if(task == null){
                val notificationStudent = NotificationDTO("Not found task by id: ${submitDocumentDTO.idTask}")
                producer.notifyStudent(notificationStudent)
                return
            }

            val document = Document(
                documentDTO.id,
                documentDTO.nameStudent,
                content = documentDTO.content,
                dateSubmit = documentDTO.dateSubmit
            )

            if (documentDTO.dateSubmit > task.dateFinalSubmit) {
                val notificationStudent = NotificationDTO("Task by ${document.id} was sent after the deadline and refused")
                producer.notifyStudent(notificationStudent)
                return
            }

            documentRepository.save(document)
            task.documents.add(document)
            taskRepository.save(task)

            val notificationStudent = NotificationDTO("Task registered!")

            producer.notifyStudent(notificationStudent)
        }
        catch (e:ConstraintViolationException) {
            logger.error("Constraint violation exception - {}", e.message)
            val notificationStudent = NotificationDTO("Error processing job submission")
            producer.notifyStudent(notificationStudent)
        }
        catch(e:Exception) {
            logger.error("Error processing job submission {}", e.message)
            val notificationStudent = NotificationDTO("Error processing job submission")
            producer.notifyStudent(notificationStudent)
        }
    }

    @KafkaListener(topics = ["task_analyzed"])
    fun notification(@Payload payload: String) {
        val resultDTO:ResultDTO = jacksonObjectMapper().readValue(payload)
        logger.info("Task analyzed => {}", resultDTO)
        taskRepository.save(Task(
            id = resultDTO.taskDTO.id,
            title = resultDTO.taskDTO.title,
            description = resultDTO.taskDTO.description,
            dateFinalSubmit = resultDTO.taskDTO.dateFinalSubmit,
            documents = resultDTO.taskDTO.documents as MutableList<Document>,
            status = resultDTO.taskDTO.status,
            average = resultDTO.average
        ))
    }
}