package com.example.gwanggo.service;

import com.example.gwanggo.entity.Advertisement;
import com.example.gwanggo.entity.User;
import com.example.gwanggo.entity.UserTag;
import com.example.gwanggo.repository.AdRepository;
import com.example.gwanggo.repository.AdTagRepository;
import com.example.gwanggo.repository.UserTagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AdService {

    private final AdRepository adRepository;
    private final UserTagRepository userTagRepository;
    private final AdTagRepository adTagRepository;

    public AdService(AdRepository adRepository, UserTagRepository userTagRepository, AdTagRepository adTagRepository) {
        this.adRepository = adRepository;
        this.userTagRepository = userTagRepository;
        this.adTagRepository = adTagRepository;
    }

    // TODO : 추천 광고 리스트
    public List<Advertisement> getAdvertisementList (User user){
        return adRepository.findAdvertisementList(user.getUserId()).get();
    }

    // TODO : 광고 cpc 계산 후 출력
    public Advertisement calculateAdAmount(String adId){
        Advertisement ad = adRepository.findAdvertisementByName(adId).get();
        ad.setTotalClick(ad.getTotalClick()+1);
        ad.setTotalAmount(ad.getTotalAmount()+ad.getCpc());
        return adRepository.save(ad);
    }
}
