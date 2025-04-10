package com.parceldelivery.parcel_backend.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "parcels")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Parcel {
    @Id
    private String id;
    private String referenceCode;

    private String senderId;    // client id
    private String receiverId;  // client id

    private String deliveryAddress;

    private ParcelStatus currentStatus;
    private LocalDateTime createdAt;

    private List<ParcelStatusLog> statusHistory;
}