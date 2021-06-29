package com.example.student.dto

import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonSerialize
data class NotificationDTO(
    val message:String
) {
    override fun toString()=message
}
