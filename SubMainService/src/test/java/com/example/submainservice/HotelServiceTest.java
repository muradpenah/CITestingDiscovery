package com.example.submainservice;

import com.example.submainservice.dto.HotelRequestDto;
import com.example.submainservice.dto.HotelResponseDto;
import com.example.submainservice.entity.Hotel;
import com.example.submainservice.mapper.HotelMapper;
import com.example.submainservice.repo.HotelRepository;
import com.example.submainservice.service.HotelService;
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
public class HotelServiceTest {

    @Mock
    private HotelMapper hotelMapper;

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelService hotelService;

    @Test
    void addHotelTest(){

        HotelRequestDto hotelRequestDto = new HotelRequestDto("Dinamo","Baku",200.0);
        Hotel hotelEntity = new Hotel(1L,"Dinamo","Baku",200.0);
        HotelResponseDto hotelResponseDto = new HotelResponseDto(1L,"Dinamo","Baku",200.0);

        when(hotelMapper.toEntity(hotelRequestDto)).thenReturn(hotelEntity);
        when(hotelRepository.save(hotelEntity)).thenReturn(hotelEntity);
        when(hotelMapper.toDto(hotelEntity)).thenReturn(hotelResponseDto);

        HotelResponseDto result = hotelService.addHotel(hotelRequestDto);
        assertNotNull(result);
        assertEquals(result.getId(),1L);
        assertEquals(result.getName(),"Dinamo");
        assertEquals(result.getCity(),"Baku");
        assertEquals(result.getPricePerNight(),200.0);

        verify(hotelMapper).toEntity(hotelRequestDto);
        verify(hotelRepository).save(hotelEntity);
        verify(hotelMapper).toDto(hotelEntity);
    }

    @Test
    void getHotelByIdTest(){

        Long hotelId = 1L;
        Hotel hotelEntity = new Hotel(hotelId,"Dinamo","Baku",200.0);
        HotelResponseDto hotelResponseDto = new HotelResponseDto(hotelId,"Dinamo","Baku",200.0);

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotelEntity));
        when(hotelMapper.toDto(hotelEntity)).thenReturn(hotelResponseDto);

        HotelResponseDto result = hotelService.getHotelById(hotelId);
        assertNotNull(result);
        assertEquals(result.getId(),hotelId);
        assertEquals(result.getName(),"Dinamo");
        assertEquals(result.getCity(),"Baku");
        assertEquals(result.getPricePerNight(),200.0);

        verify(hotelRepository).findById(hotelId);
        verify(hotelMapper).toDto(hotelEntity);
    }
}
