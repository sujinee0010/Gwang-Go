package com.example.gwanggo.domain.advertisement.dto;


import com.example.gwanggo.domain.advertisement.entity.Advertisement;
import lombok.*;

// Dto이기 떄문에 원하는 컬럼만 구성해도 된다
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementDto {

    private String name;

    private int totalClick;

    public AdvertisementDto(Advertisement advertisement) {
    }

    //private List<TagDto> tagDto;

}
