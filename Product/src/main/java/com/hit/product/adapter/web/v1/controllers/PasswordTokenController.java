package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.applications.services.EmailSenderService;
import com.hit.product.applications.services.PasswordResetTokenService;
import com.hit.product.applications.services.UserService;
import com.hit.product.domains.dtos.PasswordDto;
import com.hit.product.domains.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class PasswordTokenController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordResetTokenService passwordResetTokenService;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordDto passwordDto, HttpServletRequest request) {
        return ResponseEntity.ok().body(passwordResetTokenService.resetPassword(passwordDto, request));
    }

    @PostMapping("/savePassword")
    public ResponseEntity<?> savePassword(@RequestParam String token, @RequestBody PasswordDto passwordDto) {
        String result = passwordResetTokenService.validatePasswordResetToken(token);
        if(!result.equalsIgnoreCase("valid")) {
            return ResponseEntity.ok().body("Invalid token");
        }
        Optional<User> user = passwordResetTokenService.getUserByPasswordResetToken(token);
        if(user.isPresent()) {
            passwordResetTokenService.changePassword(user.get(), passwordDto.getNewPassword());
            return ResponseEntity.ok().body("Password Reset Successfully");
        }

        return ResponseEntity.ok().body("Invalid token");
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody PasswordDto passwordDto) {
        User user = userService.findUserByUsername(passwordDto.getUsername());
        if(!passwordResetTokenService.checkIfValidOldPassword(user, passwordDto.getOldPassword())) {
            return ResponseEntity.ok().body("Invalid Old Password");
        }

        // Save new Password
        passwordResetTokenService.changePassword(user, passwordDto.getNewPassword());
        return ResponseEntity.ok().body("Password Changed Successfully");
    }



}
