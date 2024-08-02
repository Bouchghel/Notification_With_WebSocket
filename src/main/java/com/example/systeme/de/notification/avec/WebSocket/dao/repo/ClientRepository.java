package com.example.systeme.de.notification.avec.WebSocket.dao.repo;

import com.example.systeme.de.notification.avec.WebSocket.dao.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
