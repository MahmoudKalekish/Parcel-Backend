package com.parceldelivery.parcel_backend.controller;

import com.parceldelivery.parcel_backend.service.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.parceldelivery.parcel_backend.dto.ClientRequest;
import com.parceldelivery.parcel_backend.dto.ClientResponse;
import com.parceldelivery.parcel_backend.mapper.ClientMapper;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Tag(name = "Clients")
public class ClientController {

    private final ClientService service;

    @PostMapping
    public ClientResponse create(@RequestBody @Valid ClientRequest request) {
        return ClientMapper.toResponse(service.createClient(ClientMapper.toEntity(request)));
    }

    @GetMapping
    public List<ClientResponse> getAll() {
        return service.getAllClients()
                .stream()
                .map(ClientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public ClientResponse getByEmail(@RequestParam String email) {
        return ClientMapper.toResponse(service.findByEmail(email));
    }
}