package com.parceldelivery.parcel_backend.repository;


import com.parceldelivery.parcel_backend.entity.Parcel;
import com.parceldelivery.parcel_backend.entity.ParcelStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ParcelRepository extends MongoRepository<Parcel, String> {
    List<Parcel> findByCurrentStatus(ParcelStatus status);
    List<Parcel> findBySenderId(String senderId);
    List<Parcel> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
