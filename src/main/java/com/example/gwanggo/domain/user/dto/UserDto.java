package com.example.gwanggo.domain.user.dto;

import com.example.gwanggo.domain.tag.Tag;
import com.example.gwanggo.domain.tag.TagDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    @Size(min = 3, max = 45)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    @NotNull
    @Size(min = 1, max = 1)
    private String sex;

    //private List<TagDto> tagList;
}