package com.parceldelivery.parcel_backend.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public String index() {
        return "Parcel Delivery Backend is running. Visit /swagger-ui.html for docs.";
    }
}