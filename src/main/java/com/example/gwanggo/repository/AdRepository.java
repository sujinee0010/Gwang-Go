package com.example.gwanggo.repository;


import com.example.gwanggo.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdRepository extends JpaRepository<Advertisement, Long> {

    @Query(" select at from UserTag ut inner join AdvertisementTag at on ut.tagId = at.tagId where ut.userId = ?1" )
    Optional<List<Advertisement>> findAdvertisementList(Long userId);

    Optional<Advertisement> findAdvertisementByName(String adName);


}
