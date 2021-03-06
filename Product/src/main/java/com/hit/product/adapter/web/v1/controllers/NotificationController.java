package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.services.NotificationService;
import com.hit.product.domains.dtos.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("")
    public ResponseEntity<?> getNotifications() {
        return VsResponseUtil.ok(notificationService.getNotifications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNotificationById(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(notificationService.getNotificationById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> createNotification(@RequestBody NotificationDto NotificationDto) {
        return VsResponseUtil.ok(notificationService.createNotification(NotificationDto));
    }

    @PostMapping("/{idUser}")
    public ResponseEntity<?> createNotificationForUser(@PathVariable("idUser") Long idUser, @RequestBody NotificationDto notificationDto) {
        return VsResponseUtil.ok(notificationService.createNotificationForUser(idUser, notificationDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateNotification(@PathVariable("id") Long id, @RequestBody NotificationDto notificationDto) {
        return VsResponseUtil.ok(notificationService.updateNotification(id, notificationDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(notificationService.deleteNotification(id));
    }
}
