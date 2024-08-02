package com.example.systeme.de.notification.avec.WebSocket.Service;

import com.example.systeme.de.notification.avec.WebSocket.dao.entities.Client;
import com.example.systeme.de.notification.avec.WebSocket.dao.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public List<Client> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients;
    }



    public Optional<Client> getClientById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        return clientOptional;
    }


    public Client createClient(Client client) {
        Client savedClient = clientRepository.save(client);
        return savedClient;
    }
}
