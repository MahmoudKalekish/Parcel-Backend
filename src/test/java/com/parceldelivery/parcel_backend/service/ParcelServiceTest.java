package com.parceldelivery.parcel_backend.service;

import com.parceldelivery.parcel_backend.dto.ParcelRequest;
import com.parceldelivery.parcel_backend.entity.Client;
import com.parceldelivery.parcel_backend.entity.Parcel;
import com.parceldelivery.parcel_backend.entity.ParcelStatus;
import com.parceldelivery.parcel_backend.repository.ClientRepository;
import com.parceldelivery.parcel_backend.repository.ParcelRepository;
import com.parceldelivery.parcel_backend.service.impl.ParcelServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ParcelServiceTest {

    @Mock
    private ParcelRepository parcelRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ParcelServiceImpl parcelService;

    private Client sender;
    private Client receiver;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sender = Client.builder().id("sender123").email("sender@mail.com").firstName("Sender").build();
        receiver = Client.builder().id("receiver456").email("receiver@mail.com").firstName("Receiver").build();
    }

    @Test
    void testCreateParcel() {
        Parcel request = new Parcel();
        request.setSenderId("sender123");
        request.setReceiverId("receiver456");
        request.setDeliveryAddress("Beirut");

        when(clientRepository.findById("sender123")).thenReturn(Optional.of(sender));
        when(clientRepository.findById("receiver456")).thenReturn(Optional.of(receiver));
        when(parcelRepository.save(any(Parcel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Parcel created = parcelService.createParcel(request);

        assertNotNull(created);
        assertEquals("sender123", created.getSenderId());
        assertEquals("receiver456", created.getReceiverId());
        assertEquals("Beirut", created.getDeliveryAddress());
        assertEquals(ParcelStatus.CREATED, created.getCurrentStatus());
        assertNotNull(created.getStatusHistory());
        assertFalse(created.getStatusHistory().isEmpty());
    }

    @Test
    void testUpdateParcelStatus() {
        Parcel parcel = Parcel.builder()
                .id("parcel123")
                .senderId("sender123")
                .receiverId("receiver456")
                .deliveryAddress("Beirut")
                .currentStatus(ParcelStatus.CREATED)
                .build();

        when(parcelRepository.findById("parcel123")).thenReturn(Optional.of(parcel));
        when(parcelRepository.save(any(Parcel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Parcel updated = parcelService.updateStatus("parcel123", ParcelStatus.IN_TRANSIT);

        assertEquals(ParcelStatus.IN_TRANSIT, updated.getCurrentStatus());
        assertNotNull(updated.getStatusHistory());
        assertTrue(updated.getStatusHistory().stream().anyMatch(
                log -> log.getStatus().equals(ParcelStatus.IN_TRANSIT)
        ));
    }

    @Test
    void testUpdateStatus_NotFound() {
        when(parcelRepository.findById("unknown")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> parcelService.updateStatus("unknown", ParcelStatus.CANCELLED));
    }
}