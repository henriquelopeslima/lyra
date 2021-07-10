package com.example.teacher.dto

import com.example.teacher.domain.Document
import com.example.teacher.domain.enum.TaskType
import java.util.*

data class TaskDTO(
    val id:Int?,
    val title:String,
    val description:String,
    val dateFinalSubmit: Date,
    val documents: List<Document>?,
    val status: TaskType?,
)
