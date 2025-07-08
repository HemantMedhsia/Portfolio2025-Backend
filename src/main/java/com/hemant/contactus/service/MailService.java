package com.hemant.contactus.service;

import com.hemant.contactus.model.MailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.TemplateEngine;



@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendHtmlMail(MailRequest request) {
        try {
            // 1. Fill dynamic data in HTML
            Context context = new Context();
            context.setVariable("name", request.getName());
            context.setVariable("message", request.getBody());

            // 2. Process HTML template
            String htmlContent = templateEngine.process("mail-template", context);

            // 3. Send email
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("hg3673041@gmail.com");
            helper.setTo(request.getTo());
            helper.setSubject(request.getSubject());
            helper.setText(htmlContent, true);

            javaMailSender.send(message);
            System.out.println("✅ HTML Mail sent to: " + request.getTo());

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to send email", e);
        }
    }
}
