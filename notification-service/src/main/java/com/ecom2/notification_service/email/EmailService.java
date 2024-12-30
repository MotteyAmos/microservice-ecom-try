package com.ecom2.notification_service.email;


import com.ecom2.notification_service.kafka.order.Product;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import jakarta.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;


    @Async
    public void sendPaymentSuccessEmail(
            String destinationEmail,
            String customName,
            BigDecimal amount,
            String orderReference

    )  throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        final String templateName = EmailTemplates.PAYMENT_CONFIRMATION.getTemplate();

        Map<String, Object> varibles = new HashMap<>();
        varibles.put("customerName", customName);
        varibles.put("amount", amount);
        varibles.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(varibles);
        messageHelper.setSubject(EmailTemplates.PAYMENT_CONFIRMATION.getSubject());

        try{
            String htmlTemplate = templateEngine.process(templateName, context);
            log.info(String.format("Info - Email sucessfully sent to %s with template %s", destinationEmail));
        }catch (org.springframework.messaging.MessagingException e){
            log.warn("WARNING - Cannot sent email to {}", destinationEmail);
        }
    }


    @Async
    public void sendOrderConfirmationEmail(
            String destinationEmail,
            String customName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    )  throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        final String templateName = EmailTemplates.ORDER_CONFIRMATION.getTemplate();

        Map<String, Object> varibles = new HashMap<>();
        varibles.put("customerName", customName);
        varibles.put("totalAmount", amount);
        varibles.put("orderReference", orderReference);
        varibles.put("products", products);

        Context context = new Context();
        context.setVariables(varibles);
        messageHelper.setSubject(EmailTemplates.ORDER_CONFIRMATION.getSubject());

        try{
            String htmlTemplate = templateEngine.process(templateName, context);
            log.info(String.format("Info - Email sucessfully sent to %s with template %s", destinationEmail));
        }catch (org.springframework.messaging.MessagingException e){
            log.warn("WARNING - Cannot sent email to {}", destinationEmail);
        }
    }
}
