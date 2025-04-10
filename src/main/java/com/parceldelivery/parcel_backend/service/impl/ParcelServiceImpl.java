package com.parceldelivery.parcel_backend.service.impl;


import com.parceldelivery.parcel_backend.entity.Parcel;
import com.parceldelivery.parcel_backend.entity.ParcelStatus;
import com.parceldelivery.parcel_backend.entity.ParcelStatusLog;
import com.parceldelivery.parcel_backend.exception.ResourceNotFoundException;
import com.parceldelivery.parcel_backend.repository.ParcelRepository;
import com.parceldelivery.parcel_backend.service.ParcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParcelServiceImpl implements ParcelService {

    private final ParcelRepository repository;

    @Override
    public Parcel createParcel(Parcel parcel) {
        parcel.setReferenceCode(UUID.randomUUID().toString());
        parcel.setCreatedAt(LocalDateTime.now());
        return repository.save(parcel);
    }

    @Override
    public Parcel updateStatus(String parcelId, ParcelStatus newStatus) {
        Parcel parcel = repository.findById(parcelId)
                .orElseThrow(() -> new ResourceNotFoundException("Parcel not found"));

        if (parcel.getStatusHistory() == null)
            parcel.setStatusHistory(new ArrayList<>());

        parcel.getStatusHistory().add(ParcelStatusLog.builder()
                .status(newStatus)
                .timestamp(LocalDateTime.now())
                .build());

        parcel.setCurrentStatus(newStatus);
        return repository.save(parcel);
    }

    @Override
    public List<Parcel> filterByStatus(ParcelStatus status) {
        return repository.findByCurrentStatus(status);
    }

    @Override
    public List<Parcel> filterBySender(String senderId) {
        return repository.findBySenderId(senderId);
    }

    @Override
    public List<Parcel> filterByCreationDateRange(LocalDateTime from, LocalDateTime to) {
        return repository.findByCreatedAtBetween(from, to);
    }
}
