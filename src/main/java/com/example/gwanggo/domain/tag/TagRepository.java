package com.example.gwanggo.domain.tag;

import com.example.gwanggo.domain.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository <Tag, Long> {

}

