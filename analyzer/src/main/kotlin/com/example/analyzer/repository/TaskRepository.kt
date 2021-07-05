package com.example.analyzer.repository

import com.example.analyzer.domain.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository: JpaRepository<Task, Int>