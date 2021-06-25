package com.example.gwanggo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "advertisement_tag")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long advertisementTagId;

    private Long advertisementId;

    private Long tagId;

}
