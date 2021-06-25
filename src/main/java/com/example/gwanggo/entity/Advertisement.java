package com.example.gwanggo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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

}
