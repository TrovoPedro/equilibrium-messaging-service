package com.api_disparo_email.controller

import com.api_disparo_email.dto.EmailMessage
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/emails")
class EmailController(
    private val rabbitTemplate: RabbitTemplate,
    @Value("\${rabbitmq.exchange}") private val exchange: String,
    @Value("\${rabbitmq.routing.email}") private val routingKey: String
) {

    @PostMapping("/send")
    fun send(@RequestBody msg: EmailMessage): ResponseEntity<String> {
        rabbitTemplate.convertAndSend(exchange, routingKey, msg)
        return ResponseEntity.ok("Email enviado para a fila")
    }
}
