package com.example.submainservice.service;

import com.example.submainservice.dto.HotelRequestDto;
import com.example.submainservice.dto.HotelResponseDto;
import com.example.submainservice.entity.Hotel;
import com.example.submainservice.mapper.HotelMapper;
import com.example.submainservice.repo.HotelRepository;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public HotelService(HotelRepository hotelRepository, HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }

    public HotelResponseDto addHotel(HotelRequestDto hotelRequestDto){
        Hotel hotel = hotelMapper.toEntity(hotelRequestDto);
        Hotel savedHotel = hotelRepository.save(hotel);
        return hotelMapper.toDto(savedHotel);
    }

    public HotelResponseDto getHotelById(Long id){
        Hotel hotel = hotelRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Hotel not found")
        );
        return hotelMapper.toDto(hotel);
    }
}
