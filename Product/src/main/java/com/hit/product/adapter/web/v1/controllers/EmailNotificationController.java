package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.events.SignUpEmailNotifyCompleteEvent;
import com.hit.product.applications.services.EmailNotificationService;
import com.hit.product.domains.dtos.EmailNotificationDto;
import com.hit.product.domains.entities.EmailNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/emailNotifications")
public class EmailNotificationController {

    @Autowired
    EmailNotificationService emailNotificationService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUpEmailNotification(@RequestBody EmailNotificationDto emailNotificationDto, final HttpServletRequest request) {
        EmailNotification emailNotification = emailNotificationService.signUpEmailNotification(emailNotificationDto);
        publisher.publishEvent(new SignUpEmailNotifyCompleteEvent(
                emailNotification,
                applicationUrl(request)
        ));

        return VsResponseUtil.ok(emailNotification);
    }

    private String applicationUrl(HttpServletRequest request) {
        return "https://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
