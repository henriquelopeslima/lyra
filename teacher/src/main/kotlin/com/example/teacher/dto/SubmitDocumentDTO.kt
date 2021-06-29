package com.example.teacher.dto

import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonSerialize
data class SubmitDocumentDTO(
    val documentDTO: DocumentDTO,
    val idTask:Int
)
