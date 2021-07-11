package com.example.analyzer.event

import com.example.analyzer.domain.enum.TaskType
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.example.analyzer.dto.NotificationDTO
import com.example.analyzer.dto.ResultDTO
import com.example.analyzer.dto.TaskDTO
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class Consumer (
    private val producer: Producer
) {
    private val logger = LoggerFactory.getLogger(Consumer::class.java)

    @KafkaListener(topics = ["task_analyzer"])
    fun consume(@Payload payload: String){
        val taskDTO:TaskDTO = jacksonObjectMapper().readValue(payload)

        try {
            var amount: Int? = 0

            logger.info("task => id:${taskDTO.id} title:${taskDTO.title}")

            taskDTO.documents?.forEach{ it ->
                it.grade = grade()
                it.isPlagiary = isPlagiary()
                amount = if (it.isPlagiary) it.grade!! + amount!! else amount
                logger.info("document => name:${it.nameStudent} grade:${it.grade} isPlagiary:${it.isPlagiary}")
            }

            val total = taskDTO.documents?.filter { it -> it.isPlagiary }?.count()
            val result = if (0 == total) 0 else (amount?.floorDiv(total!!))
            val resultDTO = ResultDTO(taskDTO.copy(status = TaskType.ANALYSED), result)

            producer.submitResult(resultDTO)
        } catch (e:Exception){
            logger.error("There was a problem analyzer", e.localizedMessage)
        }
    }

    fun grade() = Random.nextInt(1, 10);
    fun isPlagiary() = Random.nextBoolean();
}
