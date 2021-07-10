package com.example.analyzer.domain

import com.example.analyzer.domain.enum.TaskType
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
    val documents: MutableList<Document> = ArrayList(),
    val status: TaskType = TaskType.WAITING,
    val average: Double?
) {
    override fun toString() = "Task(id: $id, title: $title, description: $description, date final submit: $dateFinalSubmit, status $status, documents $documents)"
}
