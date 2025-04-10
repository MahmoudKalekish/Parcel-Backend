package com.parceldelivery.parcel_backend.service;

import com.parceldelivery.parcel_backend.entity.Client;


import java.util.List;

public interface ClientService {
    Client createClient(Client client);
    List<Client> getAllClients();
    Client findByEmail(String email);
}

