package com.example.systeme.de.notification.avec.WebSocket.Service;

import com.example.systeme.de.notification.avec.WebSocket.dao.entities.Client;
import com.example.systeme.de.notification.avec.WebSocket.dao.entities.Notification;
import com.example.systeme.de.notification.avec.WebSocket.dao.repo.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private NotificationRepository notificationRepository;


    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public void sendExpirationNotification(Client client) {
        logger.info("Preparing to send notification for client: {}", client.getId());

        // Create a NotificationDto object
        Notification notification= new Notification();
        notification.setMessage("le mandat de gérance pour  " + client.getTitle() + " expire dans moins de 8 jours.");
        Date currentDate = new Date();
        notification.setNotificationDate(currentDate);
        notification.setClient(client);

        // Save the notification in the database
        notificationRepository.save(notification);

        // Send the NotificationDto object as JSON
        messagingTemplate.convertAndSend("/topic/notifications", notification);

        logger.info("Sending notification: {}", notification);
    }

    public void sendExpiredNotification(Client client) {
        // Create a NotificationDto object
        Notification notification = new Notification();
        notification.setMessage("le mandat de gérance pour  " + client.getTitle() + " est expiré.");
        Date currentDate = new Date();
        notification.setNotificationDate(currentDate);
        notification.setClient(client);

        notificationRepository.save(notification);

        // Update the NotificationDto with the generated ID
        notification.setId(notification.getId());

        // Send the NotificationDto object as JSON
        messagingTemplate.convertAndSend("/topic/notifications", notification);

        logger.info("Sending notification: {}", notification);
    }


    public String deleteNotification(Long id) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        if (notificationOptional.isPresent()) {
            notificationRepository.deleteById(id);
            return "notification with id: " + id + " has been deleted successfully.";
        } else {
            throw new RuntimeException("notification not found with id: " + id);
        }
    }

    public List<Notification> getAllNotification() {
        return notificationRepository.findAll();
    }



}


