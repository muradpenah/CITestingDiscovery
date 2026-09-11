package com.example.submainservice.mapper;

import com.example.submainservice.dto.HotelRequestDto;
import com.example.submainservice.dto.HotelResponseDto;
import com.example.submainservice.entity.Hotel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    Hotel toEntity(HotelRequestDto hotelRequestDto);

    HotelResponseDto toDto(Hotel hotel);
}
