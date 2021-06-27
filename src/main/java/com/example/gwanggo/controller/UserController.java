package com.example.gwanggo.controller;


import com.example.gwanggo.dto.TagDto;
import com.example.gwanggo.dto.UserDto;
import com.example.gwanggo.service.UserService;
import com.example.gwanggo.entity.User;
import io.swagger.annotations.ApiOperation;
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


    @ApiOperation(value = "회원가입", notes = "이메일, 비밀번호, 성별 여부를 기입합니다")
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


    @ApiOperation(value = "태그 설정", notes = "유저가 선택한 태그리스트를 설정합니다")
    @PostMapping("/tag")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Void> addTag(@RequestBody List<TagDto> tagDtoList){
        User user = userService.getMyUserWithAuthorities().get();
        userService.saveUserTag(tagDtoList, user);

        return  ResponseEntity.status(HttpStatus.OK).build();
    }


}
