package com.example.gwanggo.domain.user.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "authority")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

//    @Id
//    @Column(name = "authority_id", length = 50)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long authorityId;

    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;

//    @OneToMany(mappedBy = "authority")
//    private List<UserAuthority> userAuthorities = new ArrayList<>();
}