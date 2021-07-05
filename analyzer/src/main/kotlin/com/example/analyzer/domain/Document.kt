package com.example.analyzer.domain

import java.util.*
import javax.persistence.*

@Entity
data class Document(
    @Id
    val id:Int,
    val nameStudent:String,
    val isPlagiary: Boolean = false,
    val content:String,
    val dateSubmit: Date
)
