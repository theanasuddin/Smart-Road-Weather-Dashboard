package org.project.smartroadweatherdashboard.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SendEmailRequest(
        @Email(message = "Invalid recipient email format")
        @NotBlank(message = "Recipient email is required")
        String recipient,

        @NotBlank(message = "Subject is required")
        @Size(max = 200, message = "Subject must be less than 200 characters")
        String subject,

        @NotBlank(message = "Email body is required")
        @Size(max = 5000, message = "Body must be less than 5000 characters")
        String body
) {
}
