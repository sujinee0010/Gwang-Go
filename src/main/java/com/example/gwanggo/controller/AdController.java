package com.example.gwanggo.controller;


import com.example.gwanggo.dto.AdvertisementDto;
import com.example.gwanggo.entity.Advertisement;
import com.example.gwanggo.entity.User;
import com.example.gwanggo.service.AdService;
import com.example.gwanggo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/ad")
public class AdController {
    private final UserService userService;
    private final AdService adService;

    public AdController(UserService userService, AdService adService) {
        this.userService = userService;
        this.adService = adService;
    }

    // TODO : 추천 광고 띄우기
    @GetMapping("/")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<AdvertisementDto>> getAdvertisement(){
        List<AdvertisementDto> adDtoList = new ArrayList<>();
        User user = userService.getMyUserWithAuthorities().get();
        List<Advertisement> adList = adService.getAdvertisementList(user);

        for (Advertisement ad : adList) {
            adDtoList.add(AdvertisementDto.builder()
                    .advertisementId(ad.getAdvertisementId())
                    .name(ad.getName())
                    .cpc(ad.getCpc())
                    .totalAmount(ad.getTotalAmount())
                    .totalClick(ad.getTotalClick())
                    .build()
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(adDtoList);
    }


    // TODO : 광고 클릭시 계산하기
    @PutMapping("/{adName}")
    public ResponseEntity<Advertisement> calculateCpc(@PathVariable String adName){

        //Advertisement ad = adService.calculateAdAmount(adName);

        return ResponseEntity.status(HttpStatus.OK).body(
                adService.calculateAdAmount(adName)
        );


//        return ResponseEntity.status(HttpStatus.OK).body(
//                AdvertisementDto.builder()
//                        .advertisementId(ad.getAdvertisementId())
//                        .name(ad.getName())
//                        .cpc(ad.getCpc())
//                        .totalAmount(ad.getTotalAmount())
//                        .totalClick(ad.getTotalClick())
//                        .build()
//        );
    }

}
