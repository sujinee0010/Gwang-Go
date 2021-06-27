package com.example.gwanggo.controller;


import com.example.gwanggo.dto.AdvertisementDto;
import com.example.gwanggo.service.AdvertisementService;
import com.example.gwanggo.entity.Advertisement;
import com.example.gwanggo.entity.User;
import com.example.gwanggo.service.UserService;
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

    @PutMapping("/{adName}")
    public ResponseEntity<Advertisement> calculateAdAmount(@PathVariable String adName){

        return ResponseEntity.status(HttpStatus.OK).body(advertisementService.calculateAdAmount(adName));
    }

}
