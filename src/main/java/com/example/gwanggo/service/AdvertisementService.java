package com.example.gwanggo.service;

import com.example.gwanggo.entity.AdvertisementTag;
import com.example.gwanggo.entity.Tag;
import com.example.gwanggo.repository.AdvertisementTagRepository;
import com.example.gwanggo.entity.Advertisement;
import com.example.gwanggo.repository.AdvertisementRepository;
import com.example.gwanggo.repository.UserTagRepository;
import com.example.gwanggo.entity.User;
import com.example.gwanggo.entity.UserTag;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final UserTagRepository userTagRepository;
    private final AdvertisementTagRepository advertisementTagRepository;

    public AdvertisementService(AdvertisementRepository advertisementRepository, UserTagRepository userTagRepository, AdvertisementTagRepository advertisementTagRepository) {
        this.advertisementRepository = advertisementRepository;
        this.userTagRepository = userTagRepository;
        this.advertisementTagRepository = advertisementTagRepository;
    }

    // TODO : 추천 광고 리스트
    @Transactional
    public List<Advertisement> getAdvertisementList (User user){
        List<Tag> tagList = userTagRepository.findAllByUser(user)
                                            .get()
                                            .stream()
                                            .map(UserTag::getTag)
                                            .collect(Collectors.toList());

        Set<Advertisement> advertisementList = advertisementTagRepository.findAllByTagIn(tagList)
                                                .get()
                                                .stream()
                                                .map(AdvertisementTag::getAdvertisement)
                                                .collect(Collectors.toSet());


       return advertisementList.stream().collect(Collectors.toList());
    }

    // TODO : 광고 cpc 계산 후 출력
    @Transactional
    public Advertisement calculateAdAmount(String adName){
        Advertisement ad = advertisementRepository.findAdvertisementByName(adName).get();
        ad.setTotalClick(ad.getTotalClick()+1);
        ad.setTotalAmount(ad.getTotalAmount()+ad.getCpc());
        return advertisementRepository.save(ad);
    }
}
