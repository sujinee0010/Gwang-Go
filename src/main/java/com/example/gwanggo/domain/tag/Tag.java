package com.example.gwanggo.domain.tag;

import com.example.gwanggo.domain.advertisement.entity.AdvertisementTag;
import com.example.gwanggo.domain.user.entity.UserTag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tag")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    @JsonIgnore
    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column(name = "name", length = 45, nullable = false,  unique = true )
    private String name;

    // 다대다 관계 - 중간테이블
    @OneToMany(mappedBy = "tag")
    private List<UserTag> userTags = new ArrayList<>();

    @OneToMany(mappedBy = "tag")
    private List<AdvertisementTag> advertisementTags = new ArrayList<>();


}
