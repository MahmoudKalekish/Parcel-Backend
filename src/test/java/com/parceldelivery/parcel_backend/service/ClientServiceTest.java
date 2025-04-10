package com.parceldelivery.parcel_backend.service;



import com.parceldelivery.parcel_backend.entity.Client;
import com.parceldelivery.parcel_backend.exception.ResourceNotFoundException;
import com.parceldelivery.parcel_backend.repository.ClientRepository;
import com.parceldelivery.parcel_backend.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = Client.builder()
                .id("123")
                .firstName("Mahmoud")
                .lastName("Kalekish")
                .email("mkalekish@example.com")
                .phone("123456789")
                .build();
    }

    @Test
    void testCreateClient() {
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        Client result = clientService.createClient(client);
        assertNotNull(result);
        assertEquals("Mahmoud", result.getFirstName());
    }

    @Test
    void testGetAllClients() {
        when(clientRepository.findAll()).thenReturn(List.of(client));
        assertEquals(1, clientService.getAllClients().size());
    }

    @Test
    void testFindByEmailSuccess() {
        when(clientRepository.findByEmail("mkalekish@example.com")).thenReturn(Optional.of(client));
        Client result = clientService.findByEmail("mkalekish@example.com");
        assertEquals("123", result.getId());
    }

    @Test
    void testFindByEmailNotFound() {
        when(clientRepository.findByEmail("unknown@example.com")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> clientService.findByEmail("unknown@example.com"));
    }
}