package com.example.analyzer.domain

import java.util.*
import javax.persistence.*

@Entity
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int? = null,
    val title: String,
    val description: String,
    val dateFinalSubmit: Date,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    val documents: MutableList<Document> = ArrayList()
)
