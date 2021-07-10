package com.example.teacher.domain

import java.util.*
import javax.persistence.*

@Entity
data class Document(
    @Id
    val id:Int,
    val nameStudent:String,
    val isPlagiary: Boolean = false,
    val content:String,
    val grade:Int? = null,
    val dateSubmit: Date
) {
    override fun toString() = "Document (id: $id, name student: $nameStudent, plagiary: $isPlagiary, grade: $grade content: $content, date of submit: $dateSubmit)"
}
