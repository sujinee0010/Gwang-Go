package com.example.gwanggo.dto;


import com.example.gwanggo.entity.Advertisement;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementDto {

    private Long advertisementId;

    private String name;

    private int cpc;

    private int totalAmount;

    private int totalClick;

}
