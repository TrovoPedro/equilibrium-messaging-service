package com.api_disparo_email.dto

data class EmailMessage(
    val to: String,
    val subject: String,
    val body: String,
)
