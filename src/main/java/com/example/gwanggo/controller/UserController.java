package com.example.gwanggo.controller;


import com.example.gwanggo.dto.TagDto;
import com.example.gwanggo.dto.UserDto;
import com.example.gwanggo.entity.Tag;
import com.example.gwanggo.entity.User;
import com.example.gwanggo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.Valid;


import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService ;
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    public ResponseEntity<User> signup(
            @Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<User> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
    }

    @GetMapping("/user/{email}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<User> getUserInfo(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(email).get());
    }


    // TODO : 태그 설정 , 권한 추가
    @PostMapping("/tag")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Void> addTag(@RequestBody List<TagDto> tagList){
        Long userId = userService.getMyUserWithAuthorities().get().getUserId();
        userService.saveUserTag(tagList, userId);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

}
