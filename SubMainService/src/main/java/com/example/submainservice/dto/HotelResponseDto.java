package com.example.submainservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelResponseDto {
    Long id;
    String name;
    String city;
    Double pricePerNight;
}
