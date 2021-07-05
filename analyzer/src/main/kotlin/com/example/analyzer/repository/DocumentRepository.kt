package com.example.analyzer.repository

import com.example.analyzer.domain.Document
import org.springframework.data.jpa.repository.JpaRepository

interface DocumentRepository: JpaRepository<Document, Int>