package com.example.gwanggo.domain.advertisement.repository;

import com.example.gwanggo.domain.advertisement.entity.AdvertisementTag;
import com.example.gwanggo.domain.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertisementTagRepository extends JpaRepository<AdvertisementTag , Long> {

    Optional<List<AdvertisementTag>> findAllByTagIn(List<Tag> tagList);


}
