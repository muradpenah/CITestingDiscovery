package com.example.mainservice.controller;

import com.example.mainservice.dto.ReservationRequestDto;
import com.example.mainservice.dto.ReservationResponseDto;
import com.example.mainservice.service.ReservationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mainservice")
@Tag(name = "Reservation API")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/addReservation")
    public ResponseEntity<ReservationResponseDto> post(@RequestBody ReservationRequestDto reservationRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.create(reservationRequestDto));
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<ReservationResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getById(id));
    }
}