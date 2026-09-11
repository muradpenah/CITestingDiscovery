package com.example.mainservice.client;

import com.example.mainservice.dto.HotelResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "submainservice" , path = "api/submainservice" , fallback = ExampleFallback.class)
public interface ExampleClient {

    @GetMapping("/{hotelId}")
    public  HotelResponseDto get(@PathVariable Long hotelId);
}
