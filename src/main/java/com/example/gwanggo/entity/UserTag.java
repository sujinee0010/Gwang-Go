package com.example.gwanggo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_tag")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userTagId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

}
