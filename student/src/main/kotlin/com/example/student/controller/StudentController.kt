package com.example.student.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.example.student.domain.Document
import com.example.student.domain.Task
import com.example.student.dto.DocumentDTO
import com.example.student.event.Producer
import com.example.student.repository.DocumentRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URL

private const val URL_TASKS = "http://localhost:10020/teacher/tasks"

@RestController
@RequestMapping("/student")
class StudentController(
    private val documentRepository: DocumentRepository,
    private val producer: Producer
) {
    @GetMapping("/documents")
    fun getDocuments(): ResponseEntity<List<Document>> =
        ResponseEntity(documentRepository.findAll(), HttpStatus.OK)

    @GetMapping("/tasks")
    fun getTasks(): ResponseEntity<List<Task>> {
        val response = URL(URL_TASKS).readText(Charsets.UTF_8)
        val tasks:List<Task> = jacksonObjectMapper().readValue(response)
        return ResponseEntity(tasks, HttpStatus.OK)
    }

    @PostMapping("/submitDocument/{idTask}")
    fun submitDocument(@RequestBody documentDTO: DocumentDTO, @PathVariable idTask:Int) =
        producer.submitDocument(documentDTO, idTask)
}
