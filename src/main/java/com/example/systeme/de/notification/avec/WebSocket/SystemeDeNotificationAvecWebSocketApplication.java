package com.example.systeme.de.notification.avec.WebSocket;

import com.example.systeme.de.notification.avec.WebSocket.Service.ClientService;
import com.example.systeme.de.notification.avec.WebSocket.dao.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;

@SpringBootApplication
@EnableScheduling
public class SystemeDeNotificationAvecWebSocketApplication implements CommandLineRunner {

	@Autowired
	private ClientService clientService;

	public static void main(String[] args) {
		SpringApplication.run(SystemeDeNotificationAvecWebSocketApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createInitialClient();
	}

	private void createInitialClient() {
		Client client = new Client();
		client.setTitle("Client Test");
		client.setStartDate(LocalDate.now());
		client.setEndDate(LocalDate.now().plusDays(7));
		clientService.createClient(client);
		System.out.println("Initial client created: " + client);
	}
}
