package com.example.gwanggo.dto;


import com.example.gwanggo.entity.Advertisement;
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
