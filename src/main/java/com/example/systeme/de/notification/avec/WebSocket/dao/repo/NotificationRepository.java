package com.example.systeme.de.notification.avec.WebSocket.dao.repo;

import com.example.systeme.de.notification.avec.WebSocket.dao.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
