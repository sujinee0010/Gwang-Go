package com.example.gwanggo.domain.user.service;

import com.example.gwanggo.domain.tag.TagDto;
import com.example.gwanggo.domain.tag.TagRepository;
import com.example.gwanggo.domain.user.entity.UserTag;
import com.example.gwanggo.domain.user.repository.UserTagRepository;
import com.example.gwanggo.domain.user.dto.UserDto;
import com.example.gwanggo.domain.user.entity.Authority;
import com.example.gwanggo.domain.user.entity.User;
import com.example.gwanggo.domain.user.repository.AuthorityRepository;
import com.example.gwanggo.domain.user.repository.UserRepository;
import com.example.gwanggo.domain.user.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {
    private final UserTagRepository userTagRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TagRepository tagRepository;
    private final AuthorityRepository authorityRepository;

    public UserService(UserTagRepository userTagRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, TagRepository tagRepository, AuthorityRepository authorityRepository) {
        this.userTagRepository = userTagRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tagRepository = tagRepository;
        this.authorityRepository = authorityRepository;
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



    @Transactional
    public void saveUserTag(List<TagDto> tagDtoList, User user){

        List<UserTag> userTags = tagDtoList.stream()
                                            .map(tagDto -> UserTag.builder()
                                            .tag(tagDto.toEntity())
                                            .user(user).build())
                                            .collect(Collectors.toList());


        userTagRepository.saveAll(userTags);

    }
}
