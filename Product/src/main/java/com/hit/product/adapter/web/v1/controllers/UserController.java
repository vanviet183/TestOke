package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.events.RegistrationCompleteEvent;
import com.hit.product.applications.services.PasswordResetTokenService;
import com.hit.product.applications.services.UserService;
import com.hit.product.applications.services.VerificationTokenService;
import com.hit.product.domains.dtos.UserDto;
import com.hit.product.domains.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private ApplicationEventPublisher publisher;

//    @GetMapping("")
//    public ResponseEntity<?> getUsers(@RequestParam Integer page) {
//        return ResponseEntity.ok().body(userService.getAccounts(page));
//    }

    @GetMapping("")
    public ResponseEntity<?> getUsers() {
        return VsResponseUtil.ok(userService.getListUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(userService.getUserById(id));
    }

    @GetMapping("/{id}/vouchers")
    public ResponseEntity<?> getVouchersByUserId(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(userService.getListVoucher(id));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDto userDto, final HttpServletRequest request,  BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return VsResponseUtil.ok(bindingResult.getAllErrors().stream().map(i -> i.getDefaultMessage()));
        }

        User user = userService.registerUser(userDto);
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                applicationUrl(request)
        ));
        return VsResponseUtil.ok(user);
    }

    @PostMapping("/{id}/avatar")
    public ResponseEntity<?> uploadAvatar(@PathVariable("id") Long id, @RequestParam("avatar") MultipartFile multipartFile) {
        return VsResponseUtil.ok(userService.setAvatar(id, multipartFile));
    }

    @PatchMapping("/{idAcc}")
    public ResponseEntity<?> updateUser(@PathVariable("idAcc") Long idAcc, @RequestBody UserDto userDto) {
        return VsResponseUtil.ok((userService.updateUser(idAcc, userDto)));
    }

    @DeleteMapping("/{idAcc}")
    public ResponseEntity<?> deleteUser(@PathVariable("idAcc") Long idAcc) {
        return VsResponseUtil.ok(userService.deleteUserById(idAcc));
    }

    private String applicationUrl(HttpServletRequest request) {
        return "https://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }

}
