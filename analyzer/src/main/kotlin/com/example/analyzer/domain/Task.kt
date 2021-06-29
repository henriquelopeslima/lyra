package com.example.analyzer.domain

import java.util.*
import javax.persistence.*

@Entity()
data class Task(
    @Id val id: Int,
    val title: String? = null
)
