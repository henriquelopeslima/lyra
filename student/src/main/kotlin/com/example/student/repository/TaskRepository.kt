package com.example.student.repository

import com.example.student.domain.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository: JpaRepository<Task, Int>