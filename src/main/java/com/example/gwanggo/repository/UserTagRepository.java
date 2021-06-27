package com.example.gwanggo.repository;


import com.example.gwanggo.entity.User;
import com.example.gwanggo.entity.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTagRepository extends JpaRepository<UserTag, Long> {

    Optional<List<UserTag>> findAllByUser(User user);

}
