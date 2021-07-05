package com.example.analyzer.controller

import com.example.analyzer.domain.Task
import com.example.analyzer.dto.DocumentDTO
import com.example.analyzer.event.Producer
import com.example.analyzer.repository.TaskRepository
import com.example.analyzer.utils.Const
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URL

@RestController
@RequestMapping("/analyzer")
class AnalyzerController (
    private val taskRepository: TaskRepository,
    private val producer: Producer
) {
    @GetMapping("/tasks/{idTask}")
    fun getTasks(): ResponseEntity<List<Task>> {
        val response = URL(Const().URL_TASKS).readText(Charsets.UTF_8)
        val tasks:List<Task> = jacksonObjectMapper().readValue(response)
        return ResponseEntity(tasks, HttpStatus.OK)
    }

    @PostMapping("/submitDocument/{idTask}")
    fun submitDocument(@RequestBody documentDTO: DocumentDTO, @PathVariable idTask:Int) =
        producer.submitDocument(documentDTO, idTask)
}