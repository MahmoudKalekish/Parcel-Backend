package com.parceldelivery.parcel_backend.mapper;


import com.parceldelivery.parcel_backend.dto.ParcelRequest;
import com.parceldelivery.parcel_backend.dto.ParcelResponse;
import com.parceldelivery.parcel_backend.entity.Parcel;
import com.parceldelivery.parcel_backend.entity.ParcelStatus;
import com.parceldelivery.parcel_backend.entity.ParcelStatusLog;

import java.time.LocalDateTime;
import java.util.Collections;

public class ParcelMapper {
    public static Parcel toEntity(ParcelRequest request, String referenceCode) {
        return Parcel.builder()
                .senderId(request.getSenderId())
                .receiverId(request.getReceiverId())
                .deliveryAddress(request.getDeliveryAddress())
                .referenceCode(referenceCode)
                .currentStatus(ParcelStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .statusHistory(Collections.singletonList(
                        ParcelStatusLog.builder()
                                .status(ParcelStatus.CREATED)
                                .timestamp(LocalDateTime.now())
                                .build()
                ))
                .build();
    }

    public static ParcelResponse toResponse(Parcel parcel) {
        return ParcelResponse.builder()
                .id(parcel.getId())
                .referenceCode(parcel.getReferenceCode())
                .senderId(parcel.getSenderId())
                .receiverId(parcel.getReceiverId())
                .deliveryAddress(parcel.getDeliveryAddress())
                .currentStatus(parcel.getCurrentStatus())
                .createdAt(parcel.getCreatedAt())
                .statusHistory(parcel.getStatusHistory())
                .build();
    }
}

