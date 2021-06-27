package com.example.gwanggo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Long advertisementTagId;

    @ManyToOne
    @JoinColumn(name = "advertisement_id")
    private Advertisement advertisement;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

}
