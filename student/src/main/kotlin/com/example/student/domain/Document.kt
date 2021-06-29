package com.example.student.domain

import java.util.*
import javax.persistence.*

@Entity
data class Document(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int? = null,
    val nameStudent: String,
    val isPlagiary: Boolean = false,
    val content: String,
    val dateSubmit: Date
){
    override fun toString() = "Document (id: $id, name student: $nameStudent, plagiary: $isPlagiary, content: $content, date of submit: $dateSubmit)"
}
