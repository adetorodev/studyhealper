package com.ezadetoro.studyhelper.domain.model

data class Task(
    val title: String,
    val description: String,
    val dueDate: Long,
    val priority: String,
    val relatedToSubject: String,
    val isComplete: Boolean
)
