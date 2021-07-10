package com.example.analyzer.dto

import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonSerialize
data class ResultDTO(
    val taskDTO: TaskDTO,
    val average: Int?
)
