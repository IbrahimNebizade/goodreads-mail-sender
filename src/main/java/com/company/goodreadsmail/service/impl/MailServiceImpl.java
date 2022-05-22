package com.company.goodreadsmail.service.impl;

import com.company.goodreadsmail.model.Mail;
import com.company.goodreadsmail.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @SneakyThrows
    @Override
    public void send(Mail mail) {
        log.info("ActionLog.MailServiceImpl.send.start - to: {}", mail.getTo());
        var helper = new MimeMessageHelper(javaMailSender.createMimeMessage());
        helper.setFrom(mail.getFrom(), mail.getAlias());
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());
        if (mail.isHtml()) {
            helper.setText(loadView(mail.getTemplate()), mail.isHtml());
        } else {
            helper.setText(mail.getBody(), mail.isHtml());
        }
        javaMailSender.send(helper.getMimeMessage());
        log.info("ActionLog.MailServiceImpl.send.end - to: {}", mail.getTo());
    }

    @SneakyThrows
    private String loadView(Mail.Template template) {
        var context = new Context(LocaleContextHolder.getLocale());
        context.setVariables(template.getParams());
        return templateEngine.process(template.getPath(), context);
    }
}
