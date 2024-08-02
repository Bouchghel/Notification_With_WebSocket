package com.example.systeme.de.notification.avec.WebSocket.Service;

import com.example.systeme.de.notification.avec.WebSocket.dao.entities.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ExpirationNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(ExpirationNotificationService.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private NotificationService notificationService;

    @Scheduled(cron = "0 0 */8 * * *") //verification tout les 8h
    public void checkExpirations() {
        LocalDate today = LocalDate.now();
        List<Client> clients = clientService.getAllClients();

        logger.info("Checking expirations...");
        for (Client client : clients) {
            if (client.getEndDate() != null) {
                long daysUntilExpiration = ChronoUnit.DAYS.between(today, client.getEndDate());
                if (daysUntilExpiration <= 8 && daysUntilExpiration >= 0) {
                    logger.info("Sending expiration notification to client: {}", client.getId());
                    notificationService.sendExpirationNotification(client);
                }else if (daysUntilExpiration <= 0) {
                    logger.info("Sending expired notification to client: {}", client.getId());
                    notificationService.sendExpiredNotification(client);
                }
            }
        }
    }
}
