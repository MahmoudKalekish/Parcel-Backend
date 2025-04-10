package com.parceldelivery.parcel_backend.controller;



import com.parceldelivery.parcel_backend.dto.ParcelRequest;
import com.parceldelivery.parcel_backend.dto.ParcelResponse;
import com.parceldelivery.parcel_backend.entity.Parcel;
import com.parceldelivery.parcel_backend.entity.ParcelStatus;
import com.parceldelivery.parcel_backend.mapper.ParcelMapper;
import com.parceldelivery.parcel_backend.service.ParcelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parcels")
@RequiredArgsConstructor
@Tag(name = "Parcels")
public class ParcelController {

    private final ParcelService service;

    @PostMapping
    public ParcelResponse createParcel(@RequestBody @Valid ParcelRequest request) {
        Parcel parcel = ParcelMapper.toEntity(request, null);
        return ParcelMapper.toResponse(service.createParcel(parcel));
    }

    @PutMapping("/{id}/status")
    public ParcelResponse updateStatus(@PathVariable String id, @RequestParam ParcelStatus status) {
        return ParcelMapper.toResponse(service.updateStatus(id, status));
    }

    @GetMapping("/status")
    public List<ParcelResponse> byStatus(@RequestParam ParcelStatus status) {
        return service.filterByStatus(status).stream().map(ParcelMapper::toResponse).collect(Collectors.toList());
    }

    @GetMapping("/sender")
    public List<ParcelResponse> bySender(@RequestParam String senderId) {
        return service.filterBySender(senderId).stream().map(ParcelMapper::toResponse).collect(Collectors.toList());
    }

    @GetMapping("/date")
    public List<ParcelResponse> byDateRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return service.filterByCreationDateRange(from, to).stream().map(ParcelMapper::toResponse).collect(Collectors.toList());
    }
}