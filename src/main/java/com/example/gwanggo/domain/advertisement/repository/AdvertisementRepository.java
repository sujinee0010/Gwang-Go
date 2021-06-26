package com.example.gwanggo.domain.advertisement.repository;


import com.example.gwanggo.domain.advertisement.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    Optional<Advertisement> findAdvertisementByName(String adName);
}
