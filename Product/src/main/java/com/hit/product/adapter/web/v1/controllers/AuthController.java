package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.adapter.web.v1.transfer.parameters.auth.AuthenticationRequest;
import com.hit.product.applications.constants.UrlConstant;
import com.hit.product.applications.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping (UrlConstant.Auth.GOOGLE)
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        return VsResponseUtil.ok(authService.login(authenticationRequest));
    }

    @GetMapping("/auth/login/google")
    public ResponseEntity<?> formSignUp() {
        return ResponseEntity.ok().body("Oke");
    }
}
