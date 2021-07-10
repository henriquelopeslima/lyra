package com.example.analyzer.domain

import java.util.*
import javax.persistence.*

@Entity
data class Document(
    @Id
    val id:Int,
    val nameStudent:String,
    var isPlagiary: Boolean = false,
    var grade:Int?,
    val content:String,
    val dateSubmit: Date
) {
    override fun toString() = "Document (id: $id, name student: $nameStudent, plagiary: $isPlagiary, grade: $grade content: $content, date of submit: $dateSubmit)"
}
