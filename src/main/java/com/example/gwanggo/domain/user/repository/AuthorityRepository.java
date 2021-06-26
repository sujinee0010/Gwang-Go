package com.example.gwanggo.domain.user.repository;

import com.example.gwanggo.domain.user.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Optional<Authority> findByAuthorityName(String authorityName);
}