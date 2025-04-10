package com.parceldelivery.parcel_backend.service.impl;


import com.parceldelivery.parcel_backend.entity.Client;
import com.parceldelivery.parcel_backend.exception.ResourceNotFoundException;
import com.parceldelivery.parcel_backend.repository.ClientRepository;
import com.parceldelivery.parcel_backend.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    @Override
    public Client createClient(Client client) {
        return repository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return repository.findAll();
    }

    @Override
    public Client findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with email: " + email));
    }
}