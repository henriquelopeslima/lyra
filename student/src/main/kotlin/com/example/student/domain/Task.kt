package com.example.student.domain

import com.example.student.domain.enum.TaskType
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity
data class Task(
    @Id val id: Int,
    val title: String? = null,
    val description: String? = null,
    val dateFinalSubmit: Date,
    @Transient
    @JsonIgnore
    val documents:List<Document>? = null,
    @Transient
    @JsonIgnore
    val status: TaskType? = TaskType.WAITING,
    @Transient
    @JsonIgnore
    val average: Double?
) {
    override fun toString() = "Task(id: $id, title: $title, description: $description, date final submit: $dateFinalSubmit)"
}

