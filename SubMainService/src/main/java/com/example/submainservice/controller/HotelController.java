package com.example.submainservice.controller;

import com.example.submainservice.dto.HotelRequestDto;
import com.example.submainservice.dto.HotelResponseDto;
import com.example.submainservice.service.HotelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submainservice")
@Tag(name = "Hotel API")
public class HotelController {

    private final HotelService hotelService;
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/add")
    public ResponseEntity<HotelResponseDto> post(@RequestBody HotelRequestDto hotelRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.addHotel(hotelRequestDto));
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelResponseDto> get(@PathVariable Long hotelId) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotelById(hotelId));
    }
}
