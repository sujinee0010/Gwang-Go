package com.example.gwanggo.domain.user.controller;


import com.example.gwanggo.domain.tag.TagDto;
import com.example.gwanggo.domain.user.dto.UserDto;
import com.example.gwanggo.domain.user.service.UserService;
import com.example.gwanggo.domain.user.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.Valid;


import java.util.List;

@RestController
@RequestMapping("/api/user")
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

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<User> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
    }

    @GetMapping("/{email}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<User> getUserInfo(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(email).get());
    }



    @PostMapping("/tag")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Void> addTag(@RequestBody List<TagDto> tagList){
        Long userId = userService.getMyUserWithAuthorities().get().getUserId();
        userService.saveUserTag(tagList, userId);

        return  ResponseEntity.status(HttpStatus.OK).build();
    }

}
