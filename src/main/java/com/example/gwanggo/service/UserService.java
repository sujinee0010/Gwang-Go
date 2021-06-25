package com.example.gwanggo.service;

import com.example.gwanggo.dto.TagDto;
import com.example.gwanggo.dto.UserDto;
import com.example.gwanggo.entity.Authority;
import com.example.gwanggo.entity.Tag;
import com.example.gwanggo.entity.User;
import com.example.gwanggo.entity.UserTag;
import com.example.gwanggo.repository.UserRepository;
import com.example.gwanggo.repository.UserTagRepository;
import com.example.gwanggo.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.Optional;
import java.util.List;


@Service
public class UserService {
    private final UserTagRepository userTagRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserTagRepository userTagRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userTagRepository = userTagRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Transactional
    public User signup(UserDto userDto) {
        if (userRepository.findOneWithAuthoritiesByEmail(userDto.getEmail()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        //빌더 패턴의 장점
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .sex(userDto.getSex())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String username) {
        return userRepository.findOneWithAuthoritiesByEmail(username);
    }

    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByEmail);
    }



    public void saveUserTag(List<TagDto> tagList, Long userId){
        for(TagDto tagDto : tagList){
            userTagRepository.save(UserTag
                    .builder()
                    .tagId(tagDto.getTagId())
                    .userId(userId)
                    .build());
        }

    }
}
