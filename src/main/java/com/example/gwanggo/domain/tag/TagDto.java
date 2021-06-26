package com.example.gwanggo.domain.tag;

import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {

    @NotNull
    @Size(min = 3, max = 45)
    private Long tagId;

    @NotNull
    @Size(min = 3, max = 45)
    private String name;

}
