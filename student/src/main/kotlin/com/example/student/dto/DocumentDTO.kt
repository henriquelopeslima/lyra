package com.example.student.dto

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.util.*

@JsonSerialize
data class DocumentDTO(
    val id: Int?,
    val nameStudent:String,
    val content:String,
    val dateSubmit:Date?
)
