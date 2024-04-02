package com.netdeal.Fullstack.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndividualRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @Positive
    private Long individualParent;

}
