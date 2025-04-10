package com.parceldelivery.parcel_backend.mapper;


import com.parceldelivery.parcel_backend.dto.ClientRequest;
import com.parceldelivery.parcel_backend.dto.ClientResponse;
import com.parceldelivery.parcel_backend.entity.Client;

public class ClientMapper {
    public static Client toEntity(ClientRequest request) {
        return Client.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
    }

    public static ClientResponse toResponse(Client client) {
        return ClientResponse.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .phone(client.getPhone())
                .build();
    }
}

