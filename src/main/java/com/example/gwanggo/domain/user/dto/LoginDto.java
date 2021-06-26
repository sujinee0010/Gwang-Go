package com.example.gwanggo.domain.user.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull
    @Size(min = 3, max = 45)
    private String email;

    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    @NotNull
    @Size(min = 1, max = 1)
    private String sex;
}