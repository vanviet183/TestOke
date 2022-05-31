package com.hit.product.applications.services.impl;

import com.hit.product.applications.repositories.EmailNotificationRepository;
import com.hit.product.applications.services.EmailNotificationService;
import com.hit.product.domains.dtos.EmailNotificationDto;
import com.hit.product.domains.entities.EmailNotification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    EmailNotificationRepository emailNotificationRepository;

    @Override
    public EmailNotification signUpEmailNotification(EmailNotificationDto emailNotificationDto) {
        EmailNotification emailNotification = modelMapper.map(emailNotificationDto, EmailNotification.class);
        return emailNotificationRepository.save(emailNotification);
    }
}
