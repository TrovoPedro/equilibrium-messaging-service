package com.api_disparo_email.consumer

import com.api_disparo_email.dto.EmailMessage
import com.api_disparo_email.service.EmailService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class EmailConsumer(private val emailService: EmailService) {

    @RabbitListener(queues = ["\${rabbitmq.queue.email:ea.email.queue}"])
    fun receive(msg: EmailMessage) {
        println("📥 Mensagem recebida da fila: $msg")
        emailService.sendEmail(msg)
    }
}