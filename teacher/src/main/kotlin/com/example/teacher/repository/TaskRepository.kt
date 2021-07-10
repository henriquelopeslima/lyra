package com.example.teacher.repository

import com.example.teacher.domain.Task
import com.example.teacher.domain.enum.TaskType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TaskRepository: JpaRepository<Task, Int> {
    @Query("From Task Where dateFinalSubmit < :date And status=:#{#taskType}")
    fun getAllTaskByIsAnalyzed(date: Date, @Param("taskType") taskType: TaskType = TaskType.WAITING): List<Task>
}
