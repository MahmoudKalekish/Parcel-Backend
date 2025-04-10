package com.parceldelivery.parcel_backend.entity;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParcelStatusLog {
    private ParcelStatus status;
    private LocalDateTime timestamp;
}