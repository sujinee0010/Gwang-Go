package com.example.gwanggo.domain.advertisement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "advertisement")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Advertisement {

    @JsonIgnore
    @Id
    @Column(name = "advertisement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long advertisementId;

    @Column(name = "name", length = 45, nullable = false )
    private String name;

    @Column(name = "cpc")
    private int cpc;

    @Column(name = "total_amount")
    private int totalAmount;

    @Column(name = "total_click")
    private int totalClick;

    @JsonIgnore // 데이터를 주고받을 때 해당 데이터는 응답값에서 보이지 않음
    @OneToMany(mappedBy = "advertisement")
    private List<AdvertisementTag> advertisementTags = new ArrayList<>();

}
