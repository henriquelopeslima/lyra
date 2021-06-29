package com.example.teacher.repository

import com.example.teacher.domain.Document
import org.springframework.data.jpa.repository.JpaRepository

interface DocumentRepository: JpaRepository<Document, Int>