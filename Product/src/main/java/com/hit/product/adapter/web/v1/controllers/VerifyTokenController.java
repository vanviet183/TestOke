package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.applications.services.EmailSenderService;
import com.hit.product.applications.services.VerificationTokenService;
import com.hit.product.domains.entities.User;
import com.hit.product.domains.entities.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class VerifyTokenController {

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    EmailSenderService emailSenderService;

    @GetMapping("/verifyRegistration")
    public ResponseEntity<?> verifyRegistration(@RequestParam("token") String token) {
        String request = verificationTokenService.validateVerificationToken(token);
        if(request.equalsIgnoreCase("valid")) {
            return ResponseEntity.ok().body("User Verifies Successfully");
        }
        return ResponseEntity.ok().body("Bad User");
    }

    @GetMapping("/verifyEmailNotification")
    public ResponseEntity<?> verifyEmailNotification(@RequestParam("token") String token) {
        String request = verificationTokenService.validateVerificationEmailNotificationToken(token);
        if(request.equalsIgnoreCase("valid")) {
            return ResponseEntity.ok().body("Email Notification Verifies Successfully");
        }
        return ResponseEntity.ok().body("Error");
    }

    @GetMapping("/resendVerifyToken")
    public ResponseEntity<?> resendVerifyToken(@RequestParam("token") String oldToken, HttpServletRequest request) {
        VerificationToken verificationToken = verificationTokenService.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        verificationTokenService.resendVerificationTokenMail(user, applicationUrl(request), verificationToken);
        return ResponseEntity.ok().body("Verification Link Sent");
    }

    private String applicationUrl(HttpServletRequest request) {
        return "https://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
