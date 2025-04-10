package com.parceldelivery.parcel_backend.repository;


import com.parceldelivery.parcel_backend.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClientRepository extends MongoRepository<Client, String> {
    Optional<Client> findByEmail(String email);
}

