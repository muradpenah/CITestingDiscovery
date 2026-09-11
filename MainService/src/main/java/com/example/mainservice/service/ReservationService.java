package com.example.mainservice.service;

import com.example.mainservice.client.ExampleClient;
import com.example.mainservice.dto.HotelResponseDto;
import com.example.mainservice.dto.ReservationRequestDto;
import com.example.mainservice.dto.ReservationResponseDto;
import com.example.mainservice.entity.Reservation;
import com.example.mainservice.mapper.ReservationMapper;
import com.example.mainservice.repo.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ExampleClient exampleClient;
    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;
    public ReservationService(ExampleClient exampleClient, ReservationMapper reservationMapper, ReservationRepository reservationRepository) {
        this.exampleClient = exampleClient;
        this.reservationMapper = reservationMapper;
        this.reservationRepository = reservationRepository;
    }

    public ReservationResponseDto create(ReservationRequestDto reservationRequestDto){
        HotelResponseDto hotelResponseDto = exampleClient.get(reservationRequestDto.getHotelId());
        Reservation reservation = reservationMapper.toEntity(reservationRequestDto);
        Reservation savedReservation = reservationRepository.save(reservation);

        ReservationResponseDto reservationResponseDto = reservationMapper.toDto(savedReservation);
        reservationResponseDto.setHotelName(hotelResponseDto.getName());
        reservationResponseDto.setTotalPrice(hotelResponseDto.getPricePerNight() * savedReservation.getNights());
        return reservationResponseDto;
    }

    public ReservationResponseDto getById(Long id){
        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Reservation not found")
        );
        HotelResponseDto hotelResponseDto = exampleClient.get(reservation.getHotelId());
        ReservationResponseDto reservationResponseDto = reservationMapper.toDto(reservation);
        reservationResponseDto.setHotelName(hotelResponseDto.getName());
        reservationResponseDto.setTotalPrice(hotelResponseDto.getPricePerNight() * reservation.getNights());
        return reservationResponseDto;
    }
}
