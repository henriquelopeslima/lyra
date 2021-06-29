package com.example.teacher.controller

import com.example.teacher.domain.Task
import com.example.teacher.dto.NotificationDTO
import com.example.teacher.dto.TaskDTO
import com.example.teacher.event.Producer
import com.example.teacher.repository.TaskRepository
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat
import java.util.*

@RestController
@RequestMapping("/teacher")
class TeacherController(
    private val taskRepository: TaskRepository,
    private val producer: Producer,
) {
    private val logger = LoggerFactory.getLogger(TeacherController::class.java)

    @PostMapping("/createTask")
    fun submitTask(@RequestBody taskDTO: TaskDTO){
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        taskRepository.save(Task(
            title = taskDTO.title,
            description = taskDTO.description,
            dateFinalSubmit = taskDTO.dateFinalSubmit
        ))
        logger.info("The task has been registered")
        val notificationDTO = NotificationDTO(
            "The task ${taskDTO.title} - ${(formatter.format(taskDTO.dateFinalSubmit))}(deadline) was registered by the teacher"
        )
        producer.notifyStudent(notificationDTO)
    }

    @GetMapping("/tasks")
    fun getTasks():ResponseEntity<List<Task>>{
        return ResponseEntity(taskRepository.findAll(), HttpStatus.OK)
    }
}
