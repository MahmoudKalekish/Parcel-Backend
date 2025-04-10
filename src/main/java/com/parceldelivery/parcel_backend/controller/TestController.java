package com.parceldelivery.parcel_backend.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@Tag(name = "Test")
public class TestController {

    @GetMapping
    public String test() {
        return "Hello from Swagger!";
    }
}
