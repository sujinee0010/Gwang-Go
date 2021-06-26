package com.example.gwanggo.domain.advertisement.controller;


import com.example.gwanggo.domain.advertisement.dto.AdvertisementDto;
import com.example.gwanggo.domain.advertisement.service.AdvertisementService;
import com.example.gwanggo.domain.advertisement.entity.Advertisement;
import com.example.gwanggo.domain.user.entity.User;
import com.example.gwanggo.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/ad")
public class AdvertisementController {
    private final UserService userService;
    private final AdvertisementService advertisementService;

    public AdvertisementController(UserService userService, AdvertisementService advertisementService) {
        this.userService = userService;
        this.advertisementService = advertisementService;
    }


    // TODO : 추천 광고 띄우기 .. join하고 싶다
    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<AdvertisementDto>> getAdvertisement(){

        User user = userService.getMyUserWithAuthorities().get();
        List<Advertisement> advertisementList = advertisementService.getAdvertisementList(user);

        List<AdvertisementDto> adDtoList =advertisementList.stream()
                        .map( ad -> AdvertisementDto.builder()
                        .name(ad.getName())
                        .totalClick(ad.getTotalClick())
                        .build()).collect(Collectors.toList());


        return ResponseEntity.status(HttpStatus.OK).body(adDtoList);
    }


    // TODO : 광고 클릭시 계산하기 , dto로 반환하지 않아도 되는건가? 계산된 값 보여주기
    @PutMapping("/{adName}")
    public ResponseEntity<Advertisement> calculateAdAmount(@PathVariable String adName){

        return ResponseEntity.status(HttpStatus.OK).body(advertisementService.calculateAdAmount(adName));
    }

}
