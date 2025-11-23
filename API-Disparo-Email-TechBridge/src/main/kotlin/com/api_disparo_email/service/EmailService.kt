package com.api_disparo_email.service

import com.api_disparo_email.dto.EmailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val mailSender: JavaMailSender
) {

    fun sendEmail(email: EmailMessage) {
        val message = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "UTF-8")

        helper.setFrom("example@gmail.com")

        helper.setTo(email.to)
        helper.setSubject(email.subject)
        helper.setText(email.body, true)

        mailSender.send(message)
    }
}
