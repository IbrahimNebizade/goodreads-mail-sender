package com.company.goodreadsmail.queue;

import com.company.goodreadsmail.model.Mail;
import com.company.goodreadsmail.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MailListener {
    private final MailService mailService;

    @RabbitListener(queues = "queue.mail")
    public void listenMailQueue(MessageProperties properties, Mail mail) {
        log.debug("properties: {}", properties);
        try {
            mailService.send(mail);
        } catch (Exception e) {
            log.error("Mail error: ", e);
        }
    }
}
