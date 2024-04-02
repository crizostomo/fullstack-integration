package com.netdeal.Fullstack.dto.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndividualResponse implements Serializable {

    private Long id;

    private String name;

    private Integer score;

    private List<IndividualResponse> subPersons;

}
