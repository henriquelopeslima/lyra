package com.example.student.dto

import java.util.*

data class TaskDTO(
    val id:Int,
    val title:String,
    val description:String,
    val dateFinalSubmit: Date,
)
