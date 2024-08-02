package com.example.systeme.de.notification.avec.WebSocket.controller;

import com.example.systeme.de.notification.avec.WebSocket.Service.ExpirationNotificationService;
import com.example.systeme.de.notification.avec.WebSocket.Service.NotificationService;
import com.example.systeme.de.notification.avec.WebSocket.dao.entities.Client;
import com.example.systeme.de.notification.avec.WebSocket.dao.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService ;
    private final ExpirationNotificationService expirationNotificationService;

    @Autowired
    public NotificationController(NotificationService notificationService, ExpirationNotificationService expirationNotificationService) {
        this.notificationService = notificationService;
        this.expirationNotificationService = expirationNotificationService;
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> allNotification = notificationService.getAllNotification();
        return new ResponseEntity<>(allNotification, HttpStatus.OK);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/test-expiration-check")
    public String testExpirationCheck() {
        expirationNotificationService.checkExpirations();
        return "Expiration check executed";
    }

    @PostMapping("/test")
    public ResponseEntity<String> sendTestNotification() {
        Client client = new Client(); // Créez un ClientDto test
        client.setTitle("Test Client");
        notificationService.sendExpirationNotification(client);
        return ResponseEntity.ok("Notification sent");
    }
}
