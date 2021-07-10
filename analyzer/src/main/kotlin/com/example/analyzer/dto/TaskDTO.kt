package com.example.analyzer.dto

import com.example.analyzer.domain.Document
import com.example.analyzer.domain.enum.TaskType
import java.util.*

data class TaskDTO(
    val id:Int?,
    var title:String,
    var description:String,
    var dateFinalSubmit: Date,
    var documents: List<Document>?,
    var status: TaskType?
)
