package com.parceldelivery.parcel_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ParcelRequest {
    @NotBlank
    private String senderId;

    @NotBlank
    private String receiverId;

    @NotBlank
    private String deliveryAddress;
}