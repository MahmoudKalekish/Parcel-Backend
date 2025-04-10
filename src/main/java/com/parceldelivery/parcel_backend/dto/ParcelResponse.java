package com.parceldelivery.parcel_backend.dto;


import com.parceldelivery.parcel_backend.entity.ParcelStatus;
import com.parceldelivery.parcel_backend.entity.ParcelStatusLog;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ParcelResponse {
    private String id;
    private String referenceCode;
    private String senderId;
    private String receiverId;
    private String deliveryAddress;
    private ParcelStatus currentStatus;
    private LocalDateTime createdAt;
    private List<ParcelStatusLog> statusHistory;
}
