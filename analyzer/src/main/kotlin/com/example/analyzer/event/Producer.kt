package com.example.analyzer.event

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.example.analyzer.dto.ResultDTO
import com.example.analyzer.repository.DocumentRepository
import com.example.analyzer.utils.Const
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.*

@Component
class Producer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
) {
    private val logger = LoggerFactory.getLogger(Producer::class.java)

    fun submitResult(resultDTO: ResultDTO) {
        val json = jacksonObjectMapper().writeValueAsString(resultDTO)
        logger.info(String.format("Sending document to kafka (teacher)"))
        kafkaTemplate.send(Const().TASK_ANALYZED_NOTIFICATION_SEND, json)
    }
}