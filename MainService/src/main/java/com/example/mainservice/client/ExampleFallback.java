package com.example.mainservice.client;

import com.example.mainservice.dto.HotelResponseDto;

public class ExampleFallback implements ExampleClient{

    @Override
    public HotelResponseDto get(Long hotelId) {
        HotelResponseDto hotelResponseDto = new HotelResponseDto(hotelId,"noname","nolocation",0.0);
        return hotelResponseDto;
    }
}
