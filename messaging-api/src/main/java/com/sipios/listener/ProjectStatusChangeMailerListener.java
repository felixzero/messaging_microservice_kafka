package com.sipios.listener;

import com.sipios.dto.ProjectStatusChangeDto;
import com.sipios.service.EmailService;
import com.sipios.service.TemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProjectStatusChangeMailerListener {
    private final EmailService emailService;
    private final TemplateService templateService;

    @KafkaListener(topicPattern = "${kafka.topics.project-status-changed}", autoStartup = "${kafka.enabled}")
    public void listenToProjectStatusChange(ConsumerRecord<String, ProjectStatusChangeDto> record) {
        log.info("Request for project status change received: " + record.toString());

        ProjectStatusChangeDto payload = record.value();

        if (payload.getAuthorEmailAddress() == null) {
            log.warn("Ignoring request to send an e-mail without e-mail address: " + record.toString());
            return;
        }

        try {
            emailService.sendEmail(
                payload.getAuthorEmailAddress(),
                "Votre demande",
                templateService.generateProjectStatusChangeEmail(payload)
            );
        } catch (MailException e) {
            log.error("Could not send e-mail", e);
        }
    }
}
