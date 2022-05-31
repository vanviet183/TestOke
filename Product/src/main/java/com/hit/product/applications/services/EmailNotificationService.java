package com.hit.product.applications.services;

import com.hit.product.domains.dtos.EmailNotificationDto;
import com.hit.product.domains.entities.EmailNotification;
import org.springframework.stereotype.Service;

@Service
public interface EmailNotificationService {

    EmailNotification signUpEmailNotification(EmailNotificationDto emailNotificationDto);
}
