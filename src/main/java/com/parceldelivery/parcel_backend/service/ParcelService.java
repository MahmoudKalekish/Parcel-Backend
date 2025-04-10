package com.parceldelivery.parcel_backend.service;


import com.parceldelivery.parcel_backend.entity.Parcel;
import com.parceldelivery.parcel_backend.entity.ParcelStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface ParcelService {
    Parcel createParcel(Parcel parcel);
    Parcel updateStatus(String parcelId, ParcelStatus status);
    List<Parcel> filterByStatus(ParcelStatus status);
    List<Parcel> filterBySender(String senderId);
    List<Parcel> filterByCreationDateRange(LocalDateTime from, LocalDateTime to);
}
