package com.example.analyzer.event

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.example.analyzer.domain.Document
import com.example.analyzer.dto.DocumentDTO
import com.example.analyzer.dto.SubmitDocumentDTO
import com.example.analyzer.repository.DocumentRepository
import com.example.analyzer.utils.Const
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.*

@Component
class Producer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val documentRepository: DocumentRepository
) {
    private val logger = LoggerFactory.getLogger(Producer::class.java)

    fun submitDocument(documentDTO: DocumentDTO, idTask:Int) {
        val document = Document(
            nameStudent = documentDTO.nameStudent,
            content = documentDTO.content,
            dateSubmit = Date(System.currentTimeMillis())
        )

        documentRepository.save(document)

        val submitDocumentDTO = SubmitDocumentDTO(documentDTO.copy(id = document.id, dateSubmit = document.dateSubmit), idTask)

        val json = jacksonObjectMapper().writeValueAsString(submitDocumentDTO)
        logger.info(String.format("Registering document", document))
        logger.info(String.format("Sending document to kafka"))
        kafkaTemplate.send(Const().TOPIC_DOCUMENT_RECEIVE, json)
    }
}