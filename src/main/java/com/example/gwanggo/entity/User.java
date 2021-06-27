package com.example.gwanggo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @JsonIgnore
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "email", length = 45, nullable = false,  unique = true )
    private String email;

    @JsonIgnore
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "sex", length = 1, nullable = false)
    private String sex;

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated;



    @OneToMany(mappedBy = "user")
    private List<UserTag> userTags = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;



}