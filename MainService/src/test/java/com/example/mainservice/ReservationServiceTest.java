package com.example.mainservice;

import com.example.mainservice.client.ExampleClient;
import com.example.mainservice.dto.HotelResponseDto;
import com.example.mainservice.dto.ReservationRequestDto;
import com.example.mainservice.dto.ReservationResponseDto;
import com.example.mainservice.entity.Reservation;
import com.example.mainservice.mapper.ReservationMapper;
import com.example.mainservice.repo.ReservationRepository;
import com.example.mainservice.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ExampleClient exampleClient;

    @Mock
    private ReservationMapper reservationMapper;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void createReservationTest(){

        Long hotelId = 1L;

        HotelResponseDto hotelResponseDto = new HotelResponseDto(hotelId,"Dinamo","Baku",200.0);

        ReservationRequestDto reservationRequestDto = new ReservationRequestDto("John Doe",3,hotelId);
        Reservation reservationEntity = new Reservation(1L,"John Doe",3,hotelId);
        ReservationResponseDto reservationResponseDto = new ReservationResponseDto(1L,"John Doe",3,"Dinamo",600.0);

        when(exampleClient.get(hotelId)).thenReturn(hotelResponseDto);
        when(reservationMapper.toEntity(reservationRequestDto)).thenReturn(reservationEntity);
        when(reservationRepository.save(reservationEntity)).thenReturn(reservationEntity);
        when(reservationMapper.toDto(reservationEntity)).thenReturn(reservationResponseDto);

        ReservationResponseDto result = reservationService.create(reservationRequestDto);
        assertNotNull(result);
        assertEquals(result.getId(),1L);
        assertEquals(result.getGuestName(),"John Doe");
        assertEquals(result.getNights(),3);
        assertEquals(result.getHotelName(),"Dinamo");
        assertEquals(result.getTotalPrice(),600.0);

        verify(exampleClient).get(hotelId);
        verify(reservationMapper).toEntity(reservationRequestDto);
        verify(reservationRepository).save(reservationEntity);
        verify(reservationMapper).toDto(reservationEntity);
    }

    @Test
    void getReservationByIdTest(){

        Long hotelId = 1L;
        Long reservationId = 1L;

        HotelResponseDto hotelResponseDto = new HotelResponseDto(hotelId,"Dinamo","Baku",200.0);
        Reservation reservationEntity = new Reservation(reservationId,"John Doe",3,hotelId);
        ReservationResponseDto reservationResponseDto = new ReservationResponseDto(reservationId,"John Doe",3,"Dinamo",600.0);

        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservationEntity));
        when(exampleClient.get(hotelId)).thenReturn(hotelResponseDto);
        when(reservationMapper.toDto(reservationEntity)).thenReturn(reservationResponseDto);

        ReservationResponseDto result = reservationService.getById(reservationId);
        assertNotNull(result);
        assertEquals(result.getId(),reservationId);
        assertEquals(result.getGuestName(),"John Doe");
        assertEquals(result.getNights(),3);
        assertEquals(result.getHotelName(),"Dinamo");
        assertEquals(result.getTotalPrice(),600.0);

        verify(reservationRepository).findById(reservationId);
        verify(exampleClient).get(hotelId);
        verify(reservationMapper).toDto(reservationEntity);
    }
}
