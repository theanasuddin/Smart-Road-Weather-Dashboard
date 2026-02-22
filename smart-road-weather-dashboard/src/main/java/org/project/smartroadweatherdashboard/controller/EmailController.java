package org.project.smartroadweatherdashboard.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.smartroadweatherdashboard.model.request.SendEmailRequest;
import org.project.smartroadweatherdashboard.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@Valid @RequestBody SendEmailRequest request) {
        boolean success = emailService.sendEmail(
                request.recipient(),
                request.subject(),
                request.body()
        );

        if (success) {
            return ResponseEntity.ok().body("{\"message\":\"Email sent successfully\"}");
        } else {
            return ResponseEntity.internalServerError()
                    .body("{\"error\":\"Failed to send email\"}");
        }
    }
}
