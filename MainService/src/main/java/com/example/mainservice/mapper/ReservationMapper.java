package com.example.mainservice.mapper;

import com.example.mainservice.dto.ReservationRequestDto;
import com.example.mainservice.dto.ReservationResponseDto;
import com.example.mainservice.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    // Request-də hotelId var, Entity-də də hotelId var.
    // Adlar eyni olduğu üçün @Mapping yazmağa ehtiyac yoxdur.
    Reservation toEntity(ReservationRequestDto dto);

    // hotelName məlumatı Entity-də olmadığı üçün onu ignore edirik.
    // Onu Service daxilində FeignClient-dən gələn cavabla mənimsədəcəyik.
    @Mapping(target = "hotelName", ignore = true)
    ReservationResponseDto toDto(Reservation entity);
}
