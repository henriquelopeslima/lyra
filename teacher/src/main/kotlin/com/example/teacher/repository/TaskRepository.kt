package com.example.teacher.repository

import com.example.teacher.domain.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository: JpaRepository<Task, Int>